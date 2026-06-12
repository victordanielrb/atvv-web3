package com.comunicacao.cliente.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	@Column(name = "endereco_logradouro", nullable = false)
	private String logradouro;

	@Column(name = "endereco_numero", nullable = false)
	private String numero;

	@Column(name = "endereco_bairro", nullable = false)
	private String bairro;

	@Column(name = "endereco_cidade", nullable = false)
	private String cidade;

	@Column(name = "endereco_estado", nullable = false)
	private String estado;

	@Column(name = "endereco_cep", nullable = false)
	private String cep;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
