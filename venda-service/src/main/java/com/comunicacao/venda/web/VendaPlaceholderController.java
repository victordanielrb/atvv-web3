package com.comunicacao.venda.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.comunicacao.venda.repository.VendaRepository;

@RestController
@RequestMapping("/internal/vendas")
public class VendaPlaceholderController {

	private final VendaRepository repository;

	public VendaPlaceholderController(VendaRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/ping")
	public ResponseEntity<Map<String, String>> ping(@RequestHeader("X-Loja-Id") String lojaId,
			@RequestHeader("X-User-Id") String userId,
			@RequestHeader("X-User-Roles") String roles) {
		return ResponseEntity.ok(Map.of("service", "venda-service", "lojaId", lojaId, "userId", userId,
				"roles", roles, "status", "ready"));
	}

	@GetMapping
	public ResponseEntity<List<VendaResponse>> listar(@RequestHeader("X-Loja-Id") Long lojaId,
			@RequestHeader("X-User-Id") Long userId,
			@RequestHeader("X-User-Roles") String roles,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
		if (fim.isBefore(inicio)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Periodo invalido");
		}

		List<VendaResponse> vendas = repository.findByLojaIdAndDataVendaBetweenOrderByDataVendaDesc(lojaId, inicio, fim)
				.stream()
				.map(VendaResponse::from)
				.collect(Collectors.toList());
		return ResponseEntity.ok(vendas);
	}
}
