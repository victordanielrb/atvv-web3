package com.comunicacao.cliente.web;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.comunicacao.cliente.domain.Cliente;
import com.comunicacao.cliente.domain.Documento;
import com.comunicacao.cliente.domain.Endereco;
import com.comunicacao.cliente.domain.Telefone;

public class ClienteRequest {

	@NotBlank
	private String nome;

	@NotNull
	@Valid
	private DocumentoRequest documento;

	@NotNull
	@Valid
	private EnderecoRequest endereco;

	@NotEmpty
	@Valid
	private Set<TelefoneRequest> telefones = new LinkedHashSet<>();

	public Cliente toEntity(Long lojaId) {
		Cliente cliente = new Cliente();
		cliente.setLojaId(lojaId);
		cliente.setNome(nome);
		cliente.setDocumento(new Documento(documento.getTipo(), documento.getValor()));

		Endereco enderecoEntity = new Endereco();
		enderecoEntity.setLogradouro(endereco.getLogradouro());
		enderecoEntity.setNumero(endereco.getNumero());
		enderecoEntity.setBairro(endereco.getBairro());
		enderecoEntity.setCidade(endereco.getCidade());
		enderecoEntity.setEstado(endereco.getEstado());
		enderecoEntity.setCep(endereco.getCep());
		cliente.setEndereco(enderecoEntity);

		Set<Telefone> telefoneEntities = new LinkedHashSet<>();
		for (TelefoneRequest telefone : telefones) {
			telefoneEntities.add(new Telefone(telefone.getDdd(), telefone.getNumero()));
		}
		cliente.setTelefones(telefoneEntities);
		return cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public DocumentoRequest getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoRequest documento) {
		this.documento = documento;
	}

	public EnderecoRequest getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoRequest endereco) {
		this.endereco = endereco;
	}

	public Set<TelefoneRequest> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<TelefoneRequest> telefones) {
		this.telefones = telefones;
	}

	public static class DocumentoRequest {
		@NotBlank
		private String tipo;
		@NotBlank
		private String valor;

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

	public static class EnderecoRequest {
		@NotBlank
		private String logradouro;
		@NotBlank
		private String numero;
		@NotBlank
		private String bairro;
		@NotBlank
		private String cidade;
		@NotBlank
		private String estado;
		@NotBlank
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

	public static class TelefoneRequest {
		@NotBlank
		private String ddd;
		@NotBlank
		private String numero;

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
	}
}
