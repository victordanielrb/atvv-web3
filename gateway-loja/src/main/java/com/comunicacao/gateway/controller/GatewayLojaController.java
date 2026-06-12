package com.comunicacao.gateway.controller;

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

import com.comunicacao.gateway.service.GatewayRoutingService;

@RestController
@RequestMapping("/gateway")
public class GatewayLojaController {

	private final GatewayRoutingService routingService;

	public GatewayLojaController(GatewayRoutingService routingService) {
		this.routingService = routingService;
	}

	@GetMapping("/empresas/current")
	public ResponseEntity<String> obterEmpresaAtual(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return routingService.obterEmpresaAtual(authorization);
	}

	@PostMapping(path = "/empresas", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> criarEmpresa(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
			@RequestBody String body) {
		return routingService.criarEmpresa(authorization, body);
	}

	@GetMapping("/clientes")
	public ResponseEntity<String> listarClientes(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return routingService.listarClientes(authorization);
	}

	@PostMapping(path = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> criarCliente(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
			@RequestBody String body) {
		return routingService.criarCliente(authorization, body);
	}

	@GetMapping("/funcionarios")
	public ResponseEntity<String> listarFuncionarios(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return routingService.listarFuncionarios(authorization);
	}

	@GetMapping("/vendas")
	public ResponseEntity<String> listarVendas(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
			@RequestParam String inicio,
			@RequestParam String fim) {
		return routingService.listarVendas(authorization, inicio, fim);
	}

	@GetMapping("/catalogo/itens")
	public ResponseEntity<String> listarCatalogo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return routingService.listarCatalogo(authorization);
	}

	@PostMapping(path = "/catalogo/itens", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> criarCatalogo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
			@RequestBody String body) {
		return routingService.criarCatalogo(authorization, body);
	}

	@GetMapping("/catalogo/veiculos")
	public ResponseEntity<String> listarVeiculos(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return routingService.listarVeiculos(authorization);
	}

	@PostMapping(path = "/catalogo/veiculos", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> criarVeiculo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
			@RequestBody String body) {
		return routingService.criarVeiculo(authorization, body);
	}

	@GetMapping("/inventario")
	public ResponseEntity<String> listarInventario(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return routingService.listarInventario(authorization);
	}

	@PostMapping(path = "/inventario", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> criarInventario(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
			@RequestBody String body) {
		return routingService.criarInventario(authorization, body);
	}
}
