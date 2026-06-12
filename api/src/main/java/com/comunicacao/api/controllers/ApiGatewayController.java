package com.comunicacao.api.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comunicacao.api.services.InternalGatewayClient;

@RestController
@RequestMapping("/api")
public class ApiGatewayController {

	private final InternalGatewayClient gatewayClient;

	public ApiGatewayController(InternalGatewayClient gatewayClient) {
		this.gatewayClient = gatewayClient;
	}

	@PostMapping(path = "/auth/token", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> gerarToken(@RequestBody String body) {
		return gatewayClient.postToAuth("/internal/auth/token", body);
	}

	@GetMapping("/empresas/current")
	public ResponseEntity<String> obterEmpresaAtual(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return gatewayClient.getToGateway("/gateway/empresas/current", authorization);
	}

	@PostMapping(path = "/empresas", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> criarEmpresa(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
			@RequestBody String body) {
		return gatewayClient.postToGateway("/gateway/empresas", authorization, body);
	}

	@GetMapping("/clientes")
	public ResponseEntity<String> listarClientes(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return gatewayClient.getToGateway("/gateway/clientes", authorization);
	}

	@PostMapping(path = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> criarCliente(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
			@RequestBody String body) {
		return gatewayClient.postToGateway("/gateway/clientes", authorization, body);
	}

	@GetMapping("/funcionarios")
	public ResponseEntity<String> listarFuncionarios(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return gatewayClient.getToGateway("/gateway/funcionarios", authorization);
	}

	@GetMapping("/vendas")
	public ResponseEntity<String> listarVendas(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
			@RequestParam String inicio,
			@RequestParam String fim) {
		return gatewayClient.getToGateway("/gateway/vendas", authorization, "?inicio=" + inicio + "&fim=" + fim);
	}

	@GetMapping("/catalogo/itens")
	public ResponseEntity<String> listarCatalogo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return gatewayClient.getToGateway("/gateway/catalogo/itens", authorization);
	}

	@PostMapping(path = "/catalogo/itens", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> criarItemCatalogo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
			@RequestBody String body) {
		return gatewayClient.postToGateway("/gateway/catalogo/itens", authorization, body);
	}

	@GetMapping("/catalogo/veiculos")
	public ResponseEntity<String> listarVeiculos(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return gatewayClient.getToGateway("/gateway/catalogo/veiculos", authorization);
	}

	@PostMapping(path = "/catalogo/veiculos", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> criarVeiculo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
			@RequestBody String body) {
		return gatewayClient.postToGateway("/gateway/catalogo/veiculos", authorization, body);
	}

	@GetMapping("/inventario")
	public ResponseEntity<String> listarInventario(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return gatewayClient.getToGateway("/gateway/inventario", authorization);
	}

	@PostMapping(path = "/inventario", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> criarInventario(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
			@RequestBody String body) {
		return gatewayClient.postToGateway("/gateway/inventario", authorization, body);
	}
}
