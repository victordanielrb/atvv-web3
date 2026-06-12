package com.comunicacao.funcionario.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Telefone {

	@Column(name = "ddd", nullable = false)
	private String ddd;

	@Column(name = "numero", nullable = false)
	private String numero;

	public Telefone() {
	}

	public Telefone(String ddd, String numero) {
		this.ddd = ddd;
		this.numero = numero;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Telefone telefone = (Telefone) o;
		return Objects.equals(ddd, telefone.ddd) && Objects.equals(numero, telefone.numero);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ddd, numero);
	}
}
