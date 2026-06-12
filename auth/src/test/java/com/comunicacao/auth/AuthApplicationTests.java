package com.comunicacao.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest(properties = {
		"jwt.secret=VGhpcy1pcy1hLWxvbmcgZW5vdWdoIHNlY3JldCBrZXkgZm9yIHRlc3RzIQ==",
		"jwt.expiration-seconds=3600" })
@AutoConfigureMockMvc
class AuthApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveGerarEValidarTokenComIdentidadeCompleta() throws Exception {
		MvcResult result = mockMvc.perform(post("/internal/auth/token")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"admin@loja.com\",\"senha\":\"123456\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.user_id").value(1))
				.andExpect(jsonPath("$.loja_id").value(1))
				.andExpect(jsonPath("$.email").value("admin@loja.com"))
				.andExpect(jsonPath("$.roles[0]").value("ADMIN"))
				.andReturn();

		String token = JsonPathHelper.extract(result.getResponse().getContentAsString(), "token");

		mockMvc.perform(get("/internal/auth/validate")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.valido").value(true))
				.andExpect(jsonPath("$.user_id").value(1))
				.andExpect(jsonPath("$.loja_id").value(1))
				.andExpect(jsonPath("$.email").value("admin@loja.com"))
				.andExpect(jsonPath("$.roles[0]").value("ADMIN"))
				.andExpect(jsonPath("$.roles[1]").value("FUNC"));
	}
}
