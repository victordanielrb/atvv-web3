package com.comunicacao.api;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(properties = {
		"auth.service.url=http://auth.test",
		"gateway-loja.url=http://gateway.test" })
@AutoConfigureMockMvc
class ApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RestTemplate restTemplate;

	private MockRestServiceServer server;

	@BeforeEach
	void setUp() {
		server = MockRestServiceServer.bindTo(restTemplate).build();
	}

	@Test
	void deveEncaminharConsultaDeFuncionariosParaGateway() throws Exception {
		server.expect(once(), requestTo("http://gateway.test/gateway/funcionarios"))
				.andExpect(method(HttpMethod.GET))
				.andExpect(header(HttpHeaders.AUTHORIZATION, "Bearer token-teste"))
				.andRespond(withSuccess("[{\"nome\":\"Ana Souza\"}]", MediaType.APPLICATION_JSON));

		mockMvc.perform(get("/api/funcionarios")
				.header(HttpHeaders.AUTHORIZATION, "Bearer token-teste"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Ana Souza")));

		server.verify();
	}

	@Test
	void deveEncaminharConsultaDeVendasComPeriodoParaGateway() throws Exception {
		server.expect(once(), requestTo("http://gateway.test/gateway/vendas?inicio=2026-06-01&fim=2026-06-12"))
				.andExpect(method(HttpMethod.GET))
				.andExpect(header(HttpHeaders.AUTHORIZATION, "Bearer token-teste"))
				.andRespond(withSuccess("[{\"itemNome\":\"Troca de oleo\"}]", MediaType.APPLICATION_JSON));

		mockMvc.perform(get("/api/vendas")
				.param("inicio", "2026-06-01")
				.param("fim", "2026-06-12")
				.header(HttpHeaders.AUTHORIZATION, "Bearer token-teste"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Troca de oleo")));

		server.verify();
	}
}
