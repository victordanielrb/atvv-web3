package com.comunicacao.cliente;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
class ClienteControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveCriarEListarClienteNaMesmaLoja() throws Exception {
		String payload = """
				{
				  "nome": "Maria Silva",
				  "documento": {"tipo": "CPF", "valor": "12345678900"},
				  "endereco": {
				    "logradouro": "Rua A",
				    "numero": "100",
				    "bairro": "Centro",
				    "cidade": "Campinas",
				    "estado": "SP",
				    "cep": "13000-000"
				  },
				  "telefones": [{"ddd": "19", "numero": "977777777"}]
				}
				""";

		mockMvc.perform(post("/internal/clientes")
				.header("X-Loja-Id", "22")
				.header("X-User-Id", "7")
				.header("X-User-Roles", "FUNC")
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.lojaId").value(22))
				.andExpect(jsonPath("$.nome").value("Maria Silva"))
				.andExpect(jsonPath("$.documento.tipo").value("CPF"))
				.andExpect(jsonPath("$.telefones[0].numero").value("977777777"));

		mockMvc.perform(get("/internal/clientes")
				.header("X-Loja-Id", "22")
				.header("X-User-Id", "7")
				.header("X-User-Roles", "FUNC"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].nome").value("Maria Silva"))
				.andExpect(jsonPath("$[0].documento.valor").value("12345678900"));
	}

	@Test
	void deveBloquearCadastroSemPermissao() throws Exception {
		mockMvc.perform(post("/internal/clientes")
				.header("X-Loja-Id", "22")
				.header("X-User-Id", "7")
				.header("X-User-Roles", "VISITOR")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
						  "nome": "Joao",
						  "documento": {"tipo": "CPF", "valor": "12345678900"},
						  "endereco": {
						    "logradouro": "Rua B",
						    "numero": "200",
						    "bairro": "Centro",
						    "cidade": "Campinas",
						    "estado": "SP",
						    "cep": "13000-000"
						  },
						  "telefones": [{"ddd": "19", "numero": "988888888"}]
						}
						"""))
				.andExpect(status().isForbidden());
	}
}
