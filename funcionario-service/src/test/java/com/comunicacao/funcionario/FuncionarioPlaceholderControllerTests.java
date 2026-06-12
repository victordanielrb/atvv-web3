package com.comunicacao.funcionario;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FuncionarioPlaceholderControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveResponderPingComHeaders() throws Exception {
		mockMvc.perform(get("/internal/funcionarios/ping")
				.header("X-Loja-Id", "11")
				.header("X-User-Id", "22")
				.header("X-User-Roles", "ADMIN,FUNC"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.service").value("funcionario-service"))
				.andExpect(jsonPath("$.lojaId").value("11"))
				.andExpect(jsonPath("$.userId").value("22"))
				.andExpect(jsonPath("$.roles").value("ADMIN,FUNC"))
				.andExpect(jsonPath("$.status").value("ready"));
	}

	@Test
	void deveListarFuncionariosComInformacoesCompletas() throws Exception {
		mockMvc.perform(get("/internal/funcionarios")
				.header("X-Loja-Id", "1")
				.header("X-User-Id", "22")
				.header("X-User-Roles", "ADMIN,FUNC"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].nome").value("Ana Souza"))
				.andExpect(jsonPath("$[0].perfil").value("ADMIN"))
				.andExpect(jsonPath("$[0].documento.tipo").value("CPF"))
				.andExpect(jsonPath("$[0].documento.valor").value("11122233344"))
				.andExpect(jsonPath("$[0].endereco.logradouro").value("Rua Central"))
				.andExpect(jsonPath("$[0].telefones[0].numero").value("999900001"));
	}
}
