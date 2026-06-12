package com.comunicacao.inventario;

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
class InventarioControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveListarEstoqueSeedado() throws Exception {
		mockMvc.perform(get("/internal/inventario")
				.header("X-Loja-Id", "1")
				.header("X-User-Id", "9")
				.header("X-User-Roles", "FUNC"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Filtro de ar")))
				.andExpect(content().string(containsString("\"quantidade\":17")));
	}

	@Test
	void deveCriarEstoqueComoAdmin() throws Exception {
		mockMvc.perform(post("/internal/inventario")
				.header("X-Loja-Id", "81")
				.header("X-User-Roles", "ADMIN")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
						  "itemId": 44,
						  "nome": "Bateria",
						  "quantidade": 9
						}
						"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.lojaId").value(81))
				.andExpect(jsonPath("$.itemId").value(44))
				.andExpect(jsonPath("$.nome").value("Bateria"))
				.andExpect(jsonPath("$.quantidade").value(9));
	}

	@Test
	void deveBloquearEstoqueSemAdmin() throws Exception {
		mockMvc.perform(post("/internal/inventario")
				.header("X-Loja-Id", "82")
				.header("X-User-Roles", "FUNC")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
						  "itemId": 45,
						  "nome": "Pneu",
						  "quantidade": 4
						}
						"""))
				.andExpect(status().isForbidden());
	}
}
