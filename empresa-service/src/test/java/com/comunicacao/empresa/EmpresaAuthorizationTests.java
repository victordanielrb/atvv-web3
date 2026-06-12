package com.comunicacao.empresa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class EmpresaAuthorizationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveBloquearCadastroQuandoRoleNaoForAdmin() throws Exception {
		mockMvc.perform(post("/internal/empresas")
				.header("X-Loja-Id", "1")
				.header("X-User-Id", "2")
				.header("X-User-Roles", "FUNC")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"nomeFantasia\":\"Loja Norte\",\"documento\":{\"tipo\":\"CNPJ\",\"valor\":\"12345678000199\"},\"endereco\":{\"logradouro\":\"Rua A\",\"numero\":\"10\",\"bairro\":\"Centro\",\"cidade\":\"Sao Paulo\",\"estado\":\"SP\",\"cep\":\"01000-000\"},\"telefones\":[{\"ddd\":\"11\",\"numero\":\"999999999\"}]}"))
				.andExpect(status().isForbidden());
	}
}
