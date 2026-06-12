package com.comunicacao.funcionario.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comunicacao.funcionario.repository.FuncionarioRepository;

@RestController
@RequestMapping("/internal/funcionarios")
public class FuncionarioPlaceholderController {

	private final FuncionarioRepository repository;

	public FuncionarioPlaceholderController(FuncionarioRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/ping")
	public ResponseEntity<Map<String, String>> ping(@RequestHeader("X-Loja-Id") String lojaId,
			@RequestHeader("X-User-Id") String userId,
			@RequestHeader("X-User-Roles") String roles) {
		return ResponseEntity.ok(Map.of("service", "funcionario-service", "lojaId", lojaId, "userId", userId,
				"roles", roles, "status", "ready"));
	}

	@GetMapping
	public ResponseEntity<List<FuncionarioResponse>> listar(@RequestHeader("X-Loja-Id") Long lojaId,
			@RequestHeader("X-User-Id") Long userId,
			@RequestHeader("X-User-Roles") String roles) {
		List<FuncionarioResponse> funcionarios = repository.findByLojaIdOrderByIdAsc(lojaId).stream()
				.map(FuncionarioResponse::from)
				.collect(Collectors.toList());
		return ResponseEntity.ok(funcionarios);
	}
}
