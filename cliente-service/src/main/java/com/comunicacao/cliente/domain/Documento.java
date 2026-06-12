package com.comunicacao.cliente.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Documento {

	@Column(name = "documento_tipo", nullable = false)
	private String tipo;

	@Column(name = "documento_valor", nullable = false)
	private String valor;

	public Documento() {
	}

	public Documento(String tipo, String valor) {
		this.tipo = tipo;
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
