package com.comunicacao.auth.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenValidationResponse {

	private final boolean valido;
	private final String email;

	@JsonProperty("user_id")
	private final Long userId;

	@JsonProperty("loja_id")
	private final Long lojaId;

	private final List<String> roles;

	public TokenValidationResponse(boolean valido, String email, Long userId, Long lojaId, List<String> roles) {
		this.valido = valido;
		this.email = email;
		this.userId = userId;
		this.lojaId = lojaId;
		this.roles = roles;
	}

	public boolean isValido() {
		return valido;
	}

	public String getEmail() {
		return email;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getLojaId() {
		return lojaId;
	}

	public List<String> getRoles() {
		return roles;
	}
}
