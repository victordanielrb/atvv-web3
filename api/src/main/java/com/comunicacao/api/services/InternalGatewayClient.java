package com.comunicacao.api.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class InternalGatewayClient {

	private final RestTemplate restTemplate;
	private final String authServiceUrl;
	private final String gatewayLojaUrl;

	public InternalGatewayClient(RestTemplate restTemplate,
			@Value("${auth.service.url}") String authServiceUrl,
			@Value("${gateway-loja.url}") String gatewayLojaUrl) {
		this.restTemplate = restTemplate;
		this.authServiceUrl = authServiceUrl;
		this.gatewayLojaUrl = gatewayLojaUrl;
	}

	public ResponseEntity<String> postToAuth(String path, String body) {
		return exchange(authServiceUrl + path, HttpMethod.POST, null, body);
	}

	public ResponseEntity<String> getToGateway(String path, String authorization) {
		return exchange(gatewayLojaUrl + path, HttpMethod.GET, authorization, null);
	}

	public ResponseEntity<String> postToGateway(String path, String authorization, String body) {
		return exchange(gatewayLojaUrl + path, HttpMethod.POST, authorization, body);
	}

	private ResponseEntity<String> exchange(String url, HttpMethod method, String authorization, String body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if (authorization != null && !authorization.isBlank()) {
			headers.set(HttpHeaders.AUTHORIZATION, authorization);
		}

		try {
			return restTemplate.exchange(url, method, new HttpEntity<>(body, headers), String.class);
		} catch (HttpStatusCodeException exception) {
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getResponseBodyAsString());
		}
	}
}
