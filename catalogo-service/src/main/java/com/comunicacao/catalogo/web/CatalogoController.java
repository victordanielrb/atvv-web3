package com.comunicacao.catalogo.web;

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

import com.comunicacao.catalogo.domain.ItemCatalogo;
import com.comunicacao.catalogo.domain.VeiculoCatalogo;
import com.comunicacao.catalogo.repository.ItemCatalogoRepository;
import com.comunicacao.catalogo.repository.VeiculoCatalogoRepository;

@RestController
@RequestMapping("/internal/catalogo")
public class CatalogoController {

	private final ItemCatalogoRepository itemRepository;
	private final VeiculoCatalogoRepository veiculoRepository;

	public CatalogoController(ItemCatalogoRepository itemRepository, VeiculoCatalogoRepository veiculoRepository) {
		this.itemRepository = itemRepository;
		this.veiculoRepository = veiculoRepository;
	}

	@GetMapping("/itens")
	public ResponseEntity<List<ItemCatalogo>> listarItens(@RequestHeader("X-Loja-Id") Long lojaId,
			@RequestHeader("X-User-Id") Long userId,
			@RequestHeader("X-User-Roles") String roles) {
		return ResponseEntity.ok(itemRepository.findByLojaId(lojaId));
	}

	@PostMapping("/itens")
	public ResponseEntity<ItemCatalogo> criarItem(@RequestHeader("X-Loja-Id") Long lojaId,
			@RequestHeader("X-User-Roles") String roles,
			@Valid @RequestBody ItemCatalogoRequest request) {
		validarAdmin(roles);
		return ResponseEntity.status(HttpStatus.CREATED).body(itemRepository.save(request.toEntity(lojaId)));
	}

	@GetMapping("/veiculos")
	public ResponseEntity<List<VeiculoCatalogo>> listarVeiculos(@RequestHeader("X-Loja-Id") Long lojaId,
			@RequestHeader("X-User-Id") Long userId,
			@RequestHeader("X-User-Roles") String roles) {
		return ResponseEntity.ok(veiculoRepository.findByLojaId(lojaId));
	}

	@PostMapping("/veiculos")
	public ResponseEntity<VeiculoCatalogo> criarVeiculo(@RequestHeader("X-Loja-Id") Long lojaId,
			@RequestHeader("X-User-Roles") String roles,
			@Valid @RequestBody VeiculoCatalogoRequest request) {
		if (!roles.contains("FUNC") && !roles.contains("ADMIN")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario sem permissao para cadastrar veiculo");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculoRepository.save(request.toEntity(lojaId)));
	}

	private void validarAdmin(String roles) {
		if (!roles.contains("ADMIN")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Somente ADMIN pode cadastrar item de catalogo");
		}
	}
}
