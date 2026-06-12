package com.comunicacao.gateway;

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
		"empresa.service.url=http://empresa.test",
		"cliente.service.url=http://cliente.test",
		"funcionario.service.url=http://funcionario.test",
		"venda.service.url=http://venda.test",
		"catalogo.service.url=http://catalogo.test",
		"inventario.service.url=http://inventario.test" })
@AutoConfigureMockMvc
class GatewayLojaApplicationTests {

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
	void devePropagarLojaIdParaEmpresaService() throws Exception {
		server.expect(once(), requestTo("http://auth.test/internal/auth/validate"))
				.andExpect(method(HttpMethod.GET))
				.andExpect(header(HttpHeaders.AUTHORIZATION, "Bearer token-teste"))
				.andRespond(withSuccess(
						"{\"valido\":true,\"email\":\"admin@loja.com\",\"user_id\":9,\"loja_id\":42,\"roles\":[\"ADMIN\",\"FUNC\"]}",
						MediaType.APPLICATION_JSON));

		server.expect(once(), requestTo("http://empresa.test/internal/empresas/current"))
				.andExpect(method(HttpMethod.GET))
				.andExpect(header("X-Loja-Id", "42"))
				.andExpect(header("X-User-Id", "9"))
				.andExpect(header("X-User-Roles", "ADMIN,FUNC"))
				.andRespond(withSuccess("{\"nomeFantasia\":\"Loja 42\"}", MediaType.APPLICATION_JSON));

		mockMvc.perform(get("/gateway/empresas/current")
				.header(HttpHeaders.AUTHORIZATION, "Bearer token-teste"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Loja 42")));

		server.verify();
	}

	@Test
	void deveEncaminharConsultaDeFuncionarios() throws Exception {
		server.expect(once(), requestTo("http://auth.test/internal/auth/validate"))
				.andExpect(method(HttpMethod.GET))
				.andExpect(header(HttpHeaders.AUTHORIZATION, "Bearer token-teste"))
				.andRespond(withSuccess(
						"{\"valido\":true,\"email\":\"admin@loja.com\",\"user_id\":9,\"loja_id\":42,\"roles\":[\"ADMIN\"]}",
						MediaType.APPLICATION_JSON));

		server.expect(once(), requestTo("http://funcionario.test/internal/funcionarios"))
				.andExpect(method(HttpMethod.GET))
				.andExpect(header("X-Loja-Id", "42"))
				.andExpect(header("X-User-Id", "9"))
				.andExpect(header("X-User-Roles", "ADMIN"))
				.andRespond(withSuccess("[{\"nome\":\"Ana Souza\"}]", MediaType.APPLICATION_JSON));

		mockMvc.perform(get("/gateway/funcionarios")
				.header(HttpHeaders.AUTHORIZATION, "Bearer token-teste"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Ana Souza")));

		server.verify();
	}

	@Test
	void deveEncaminharConsultaDeVendasComPeriodo() throws Exception {
		server.expect(once(), requestTo("http://auth.test/internal/auth/validate"))
				.andExpect(method(HttpMethod.GET))
				.andExpect(header(HttpHeaders.AUTHORIZATION, "Bearer token-teste"))
				.andRespond(withSuccess(
						"{\"valido\":true,\"email\":\"admin@loja.com\",\"user_id\":9,\"loja_id\":42,\"roles\":[\"ADMIN\"]}",
						MediaType.APPLICATION_JSON));

		server.expect(once(), requestTo("http://venda.test/internal/vendas?inicio=2026-06-01&fim=2026-06-12"))
				.andExpect(method(HttpMethod.GET))
				.andExpect(header("X-Loja-Id", "42"))
				.andExpect(header("X-User-Id", "9"))
				.andExpect(header("X-User-Roles", "ADMIN"))
				.andRespond(withSuccess("[{\"itemNome\":\"Troca de oleo\"}]", MediaType.APPLICATION_JSON));

		mockMvc.perform(get("/gateway/vendas")
				.param("inicio", "2026-06-01")
				.param("fim", "2026-06-12")
				.header(HttpHeaders.AUTHORIZATION, "Bearer token-teste"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Troca de oleo")));

		server.verify();
	}
}
