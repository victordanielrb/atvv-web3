package com.comunicacao.catalogo;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CatalogoControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveListarItensEVeiculosSeedados() throws Exception {
		mockMvc.perform(get("/internal/catalogo/itens")
				.header("X-Loja-Id", "1")
				.header("X-User-Id", "9")
				.header("X-User-Roles", "FUNC"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Troca de oleo")))
				.andExpect(content().string(containsString("Filtro de ar")));

		mockMvc.perform(get("/internal/catalogo/veiculos")
				.header("X-Loja-Id", "1")
				.header("X-User-Id", "9")
				.header("X-User-Roles", "FUNC"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("ABC1D23")))
				.andExpect(content().string(containsString("Maria")));
	}

	@Test
	void deveCriarItemComoAdmin() throws Exception {
		mockMvc.perform(post("/internal/catalogo/itens")
				.header("X-Loja-Id", "77")
				.header("X-User-Roles", "ADMIN")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
						  "nome": "Alinhamento",
						  "descricao": "Alinhamento e balanceamento",
						  "tipo": "SERVICO",
						  "valor": 149.90
						}
						"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.lojaId").value(77))
				.andExpect(jsonPath("$.nome").value("Alinhamento"))
				.andExpect(jsonPath("$.tipo").value("SERVICO"));

		mockMvc.perform(get("/internal/catalogo/itens")
				.header("X-Loja-Id", "77")
				.header("X-User-Id", "9")
				.header("X-User-Roles", "ADMIN"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].nome").value("Alinhamento"));
	}

	@Test
	void deveBloquearItemSemPermissaoDeAdmin() throws Exception {
		mockMvc.perform(post("/internal/catalogo/itens")
				.header("X-Loja-Id", "78")
				.header("X-User-Roles", "FUNC")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
						  "nome": "Pneu",
						  "descricao": "Pneu aro 15",
						  "tipo": "MERCADORIA",
						  "valor": 399.90
						}
						"""))
				.andExpect(status().isForbidden());
	}

	@Test
	void deveCriarVeiculoComoFuncionario() throws Exception {
		mockMvc.perform(post("/internal/catalogo/veiculos")
				.header("X-Loja-Id", "79")
				.header("X-User-Roles", "FUNC")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
						  "placa": "XYZ9A99",
						  "modelo": "Hilux",
						  "marca": "Toyota",
						  "clienteNome": "Carlos"
						}
						"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.lojaId").value(79))
				.andExpect(jsonPath("$.placa").value("XYZ9A99"))
				.andExpect(jsonPath("$.clienteNome").value("Carlos"));
	}
}
