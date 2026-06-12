package com.comunicacao.auth.controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comunicacao.auth.domain.AuthUser;
import com.comunicacao.auth.dto.TokenRequest;
import com.comunicacao.auth.dto.TokenResponse;
import com.comunicacao.auth.dto.TokenValidationResponse;
import com.comunicacao.auth.service.AuthService;
import com.comunicacao.auth.service.JwtService;

@RestController
@RequestMapping("/internal/auth")
public class AuthController {

	private final JwtService jwtService;
	private final AuthService authService;

	public AuthController(JwtService jwtService, AuthService authService) {
		this.jwtService = jwtService;
		this.authService = authService;
	}

	@PostMapping("/token")
	public ResponseEntity<TokenResponse> gerarToken(@Valid @RequestBody TokenRequest request) {
		AuthUser user = authService.autenticar(request);
		var roles = authService.expandirRoles(user);
		String token = jwtService.gerarToken(user, roles);
		return ResponseEntity.ok(new TokenResponse(token, user.getUserId(), user.getLojaId(), roles, user.getEmail()));
	}

	@GetMapping("/validate")
	public ResponseEntity<TokenValidationResponse> validarToken(
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return ResponseEntity.ok(jwtService.validarToken(authorization));
	}
}
