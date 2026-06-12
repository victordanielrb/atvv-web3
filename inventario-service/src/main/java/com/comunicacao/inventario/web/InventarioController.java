package com.comunicacao.inventario.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.comunicacao.inventario.domain.EstoqueItem;
import com.comunicacao.inventario.repository.EstoqueItemRepository;

@RestController
@RequestMapping("/internal/inventario")
public class InventarioController {

	private final EstoqueItemRepository repository;

	public InventarioController(EstoqueItemRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<EstoqueItem>> listar(@RequestHeader("X-Loja-Id") Long lojaId,
			@RequestHeader("X-User-Id") Long userId,
			@RequestHeader("X-User-Roles") String roles) {
		return ResponseEntity.ok(repository.findByLojaId(lojaId));
	}

	@PostMapping
	public ResponseEntity<EstoqueItem> criar(@RequestHeader("X-Loja-Id") Long lojaId,
			@RequestHeader("X-User-Roles") String roles,
			@Valid @RequestBody EstoqueItemRequest request) {
		if (!roles.contains("ADMIN")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Somente ADMIN pode atualizar estoque");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(request.toEntity(lojaId)));
	}
}
