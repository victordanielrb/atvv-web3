package com.comunicacao.auth.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.comunicacao.auth.domain.AuthUser;
import com.comunicacao.auth.dto.TokenRequest;
import com.comunicacao.auth.repository.AuthUserRepository;

@Service
public class AuthService {

	private final AuthUserRepository authUserRepository;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public AuthService(AuthUserRepository authUserRepository) {
		this.authUserRepository = authUserRepository;
	}

	public AuthUser autenticar(TokenRequest request) {
		AuthUser user = authUserRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais invalidas"));

		if (!passwordEncoder.matches(request.getSenha(), user.getSenhaHash())) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais invalidas");
		}

		return user;
	}

	public List<String> expandirRoles(AuthUser user) {
		LinkedHashSet<String> expanded = new LinkedHashSet<>();
		for (String role : user.getRoles()) {
			String normalized = role.toUpperCase();
			expanded.add(normalized);
			if ("ADMIN".equals(normalized)) {
				expanded.add("FUNC");
			}
		}
		return new ArrayList<>(expanded);
	}

	@PostConstruct
	void seedDefaultUsers() {
		if (authUserRepository.count() > 0) {
			return;
		}

		AuthUser admin = new AuthUser();
		admin.setUserId(1L);
		admin.setEmail("admin@loja.com");
		admin.setSenhaHash(passwordEncoder.encode("123456"));
		admin.setLojaId(1L);
		admin.setRoles(new LinkedHashSet<>(List.of("ADMIN")));

		AuthUser funcionario = new AuthUser();
		funcionario.setUserId(2L);
		funcionario.setEmail("func@loja.com");
		funcionario.setSenhaHash(passwordEncoder.encode("123456"));
		funcionario.setLojaId(1L);
		funcionario.setRoles(new LinkedHashSet<>(List.of("FUNC")));

		authUserRepository.saveAll(List.of(admin, funcionario));
	}
}
