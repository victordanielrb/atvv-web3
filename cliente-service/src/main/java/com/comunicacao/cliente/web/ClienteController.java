package com.comunicacao.cliente.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comunicacao.cliente.domain.Cliente;
import com.comunicacao.cliente.repository.ClienteRepository;

@RestController
@RequestMapping("/internal/clientes")
public class ClienteController {

	private final ClienteRepository clienteRepository;

	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@GetMapping
	public ResponseEntity<List<ClienteResponse>> listar(@RequestHeader("X-Loja-Id") Long lojaId,
			@RequestHeader("X-User-Id") Long userId,
			@RequestHeader("X-User-Roles") String roles) {
		List<ClienteResponse> clientes = clienteRepository.findByLojaId(lojaId).stream()
				.map(ClienteResponse::from)
				.collect(Collectors.toList());
		return ResponseEntity.ok(clientes);
	}

	@PostMapping
	public ResponseEntity<ClienteResponse> criar(@RequestHeader("X-Loja-Id") Long lojaId,
			@RequestHeader("X-User-Id") Long userId,
			@RequestHeader("X-User-Roles") String roles,
			@Valid @RequestBody ClienteRequest request) {
		validarPermissaoFuncOuAdmin(roles);
		Cliente cliente = clienteRepository.save(request.toEntity(lojaId));
		return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponse.from(cliente));
	}

	private void validarPermissaoFuncOuAdmin(String roles) {
		if (!roles.contains("FUNC") && !roles.contains("ADMIN")) {
			throw new org.springframework.web.server.ResponseStatusException(HttpStatus.FORBIDDEN,
					"Usuario sem permissao para criar cliente");
		}
	}
}
