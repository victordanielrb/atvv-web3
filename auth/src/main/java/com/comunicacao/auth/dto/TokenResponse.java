package com.comunicacao.auth.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {

	private final String token;
	private final String tipo = "Bearer";

	@JsonProperty("user_id")
	private final Long userId;

	@JsonProperty("loja_id")
	private final Long lojaId;

	private final List<String> roles;
	private final String email;

	public TokenResponse(String token, Long userId, Long lojaId, List<String> roles, String email) {
		this.token = token;
		this.userId = userId;
		this.lojaId = lojaId;
		this.roles = roles;
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
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

	public String getEmail() {
		return email;
	}
}
