package com.comunicacao.venda;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class VendaPlaceholderControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveResponderPingComHeaders() throws Exception {
		mockMvc.perform(get("/internal/vendas/ping")
				.header("X-Loja-Id", "11")
				.header("X-User-Id", "22")
				.header("X-User-Roles", "ADMIN,FUNC"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.service").value("venda-service"))
				.andExpect(jsonPath("$.lojaId").value("11"))
				.andExpect(jsonPath("$.userId").value("22"))
				.andExpect(jsonPath("$.roles").value("ADMIN,FUNC"))
				.andExpect(jsonPath("$.status").value("ready"));
	}

	@Test
	void deveListarVendasPorPeriodo() throws Exception {
		LocalDate inicio = LocalDate.now().minusDays(10);
		LocalDate fim = LocalDate.now();

		mockMvc.perform(get("/internal/vendas")
				.param("inicio", inicio.toString())
				.param("fim", fim.toString())
				.header("X-Loja-Id", "1")
				.header("X-User-Id", "22")
				.header("X-User-Roles", "ADMIN,FUNC"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].itemNome").value("Pastilha de freio"))
				.andExpect(jsonPath("$[0].tipoItem").value("PECA"))
				.andExpect(jsonPath("$[0].lojaId").value(1))
				.andExpect(jsonPath("$[0].dataVenda").exists());
	}

	@Test
	void deveRejeitarPeriodoInvalido() throws Exception {
		mockMvc.perform(get("/internal/vendas")
				.param("inicio", "2026-06-12")
				.param("fim", "2026-06-01")
				.header("X-Loja-Id", "1")
				.header("X-User-Id", "22")
				.header("X-User-Roles", "ADMIN,FUNC"))
				.andExpect(status().isBadRequest());
	}
}
