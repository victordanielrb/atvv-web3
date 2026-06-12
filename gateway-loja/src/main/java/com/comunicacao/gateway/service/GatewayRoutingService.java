package com.comunicacao.gateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.comunicacao.gateway.dto.TokenValidationResponse;

@Service
public class GatewayRoutingService {

	private static final String LOJA_HEADER = "X-Loja-Id";
	private static final String USER_HEADER = "X-User-Id";
	private static final String ROLES_HEADER = "X-User-Roles";

	private final RestTemplate restTemplate;
	private final String authServiceUrl;
	private final String empresaServiceUrl;
	private final String clienteServiceUrl;
	private final String funcionarioServiceUrl;
	private final String vendaServiceUrl;
	private final String catalogoServiceUrl;
	private final String inventarioServiceUrl;

	public GatewayRoutingService(RestTemplate restTemplate,
			@Value("${auth.service.url}") String authServiceUrl,
			@Value("${empresa.service.url}") String empresaServiceUrl,
			@Value("${cliente.service.url}") String clienteServiceUrl,
			@Value("${funcionario.service.url}") String funcionarioServiceUrl,
			@Value("${venda.service.url}") String vendaServiceUrl,
			@Value("${catalogo.service.url}") String catalogoServiceUrl,
			@Value("${inventario.service.url}") String inventarioServiceUrl) {
		this.restTemplate = restTemplate;
		this.authServiceUrl = authServiceUrl;
		this.empresaServiceUrl = empresaServiceUrl;
		this.clienteServiceUrl = clienteServiceUrl;
		this.funcionarioServiceUrl = funcionarioServiceUrl;
		this.vendaServiceUrl = vendaServiceUrl;
		this.catalogoServiceUrl = catalogoServiceUrl;
		this.inventarioServiceUrl = inventarioServiceUrl;
	}

	public ResponseEntity<String> obterEmpresaAtual(String authorization) {
		TokenValidationResponse token = validarToken(authorization);
		return encaminhar(empresaServiceUrl + "/internal/empresas/current", HttpMethod.GET, token, null);
	}

	public ResponseEntity<String> criarEmpresa(String authorization, String body) {
		TokenValidationResponse token = validarToken(authorization);
		return encaminhar(empresaServiceUrl + "/internal/empresas", HttpMethod.POST, token, body);
	}

	public ResponseEntity<String> listarClientes(String authorization) {
		TokenValidationResponse token = validarToken(authorization);
		return encaminhar(clienteServiceUrl + "/internal/clientes", HttpMethod.GET, token, null);
	}

	public ResponseEntity<String> criarCliente(String authorization, String body) {
		TokenValidationResponse token = validarToken(authorization);
		return encaminhar(clienteServiceUrl + "/internal/clientes", HttpMethod.POST, token, body);
	}

	public ResponseEntity<String> listarFuncionarios(String authorization) {
		TokenValidationResponse token = validarToken(authorization);
		return encaminhar(funcionarioServiceUrl + "/internal/funcionarios", HttpMethod.GET, token, null);
	}

	public ResponseEntity<String> listarVendas(String authorization, String inicio, String fim) {
		TokenValidationResponse token = validarToken(authorization);
		String url = UriComponentsBuilder.fromHttpUrl(vendaServiceUrl + "/internal/vendas")
				.queryParam("inicio", inicio)
				.queryParam("fim", fim)
				.toUriString();
		return encaminhar(url, HttpMethod.GET, token, null);
	}

	public ResponseEntity<String> listarCatalogo(String authorization) {
		TokenValidationResponse token = validarToken(authorization);
		return encaminhar(catalogoServiceUrl + "/internal/catalogo/itens", HttpMethod.GET, token, null);
	}

	public ResponseEntity<String> criarCatalogo(String authorization, String body) {
		TokenValidationResponse token = validarToken(authorization);
		return encaminhar(catalogoServiceUrl + "/internal/catalogo/itens", HttpMethod.POST, token, body);
	}

	public ResponseEntity<String> listarVeiculos(String authorization) {
		TokenValidationResponse token = validarToken(authorization);
		return encaminhar(catalogoServiceUrl + "/internal/catalogo/veiculos", HttpMethod.GET, token, null);
	}

	public ResponseEntity<String> criarVeiculo(String authorization, String body) {
		TokenValidationResponse token = validarToken(authorization);
		return encaminhar(catalogoServiceUrl + "/internal/catalogo/veiculos", HttpMethod.POST, token, body);
	}

	public ResponseEntity<String> listarInventario(String authorization) {
		TokenValidationResponse token = validarToken(authorization);
		return encaminhar(inventarioServiceUrl + "/internal/inventario", HttpMethod.GET, token, null);
	}

	public ResponseEntity<String> criarInventario(String authorization, String body) {
		TokenValidationResponse token = validarToken(authorization);
		return encaminhar(inventarioServiceUrl + "/internal/inventario", HttpMethod.POST, token, body);
	}

	private TokenValidationResponse validarToken(String authorization) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, authorization);
		return restTemplate.exchange(authServiceUrl + "/internal/auth/validate", HttpMethod.GET,
				new HttpEntity<>(null, headers), TokenValidationResponse.class).getBody();
	}

	private ResponseEntity<String> encaminhar(String url, HttpMethod method, TokenValidationResponse token, String body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(LOJA_HEADER, String.valueOf(token.getLojaId()));
		headers.set(USER_HEADER, String.valueOf(token.getUserId()));
		headers.set(ROLES_HEADER, String.join(",", token.getRoles()));

		try {
			return restTemplate.exchange(url, method, new HttpEntity<>(body, headers), String.class);
		} catch (HttpStatusCodeException exception) {
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getResponseBodyAsString());
		}
	}
}
