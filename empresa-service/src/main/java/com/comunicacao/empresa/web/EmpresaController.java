package com.comunicacao.empresa.web;

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

import com.comunicacao.empresa.domain.Empresa;
import com.comunicacao.empresa.repository.EmpresaRepository;

@RestController
@RequestMapping("/internal/empresas")
public class EmpresaController {

	private final EmpresaRepository empresaRepository;

	public EmpresaController(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}

	@GetMapping("/current")
	public ResponseEntity<EmpresaResponse> obterEmpresaAtual(@RequestHeader("X-Loja-Id") Long lojaId,
			@RequestHeader("X-User-Id") Long userId,
			@RequestHeader("X-User-Roles") String roles) {
		Empresa empresa = empresaRepository.findByLojaId(lojaId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa da loja nao encontrada"));
		return ResponseEntity.ok(EmpresaResponse.from(empresa));
	}

	@PostMapping
	public ResponseEntity<EmpresaResponse> criar(@RequestHeader("X-Loja-Id") Long lojaId,
			@RequestHeader("X-User-Id") Long userId,
			@RequestHeader("X-User-Roles") String roles,
			@Valid @RequestBody EmpresaRequest request) {
		validarPermissaoAdmin(roles);
		if (empresaRepository.findByLojaId(lojaId).isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Loja ja cadastrada para esta empresa");
		}

		Empresa empresa = empresaRepository.save(request.toEntity(lojaId));
		return ResponseEntity.status(HttpStatus.CREATED).body(EmpresaResponse.from(empresa));
	}

	private void validarPermissaoAdmin(String roles) {
		if (!roles.contains("ADMIN")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Somente ADMIN pode cadastrar empresa");
		}
	}
}
