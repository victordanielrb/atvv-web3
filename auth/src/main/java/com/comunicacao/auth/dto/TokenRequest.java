package com.comunicacao.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class TokenRequest {

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
