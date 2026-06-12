package com.comunicacao.gateway.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenValidationResponse {

	private boolean valido;
	private String email;

	@JsonProperty("user_id")
	private Long userId;

	@JsonProperty("loja_id")
	private Long lojaId;

	private List<String> roles;

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLojaId() {
		return lojaId;
	}

	public void setLojaId(Long lojaId) {
		this.lojaId = lojaId;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
