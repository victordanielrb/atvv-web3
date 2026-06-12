package com.comunicacao.empresa.web;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.comunicacao.empresa.domain.Documento;
import com.comunicacao.empresa.domain.Empresa;
import com.comunicacao.empresa.domain.Endereco;
import com.comunicacao.empresa.domain.Telefone;

public class EmpresaRequest {

	@NotBlank
	private String nomeFantasia;

	@NotNull
	@Valid
	private DocumentoRequest documento;

	@NotNull
	@Valid
	private EnderecoRequest endereco;

	@NotEmpty
	@Valid
	private Set<TelefoneRequest> telefones = new LinkedHashSet<>();

	public Empresa toEntity(Long lojaId) {
		Empresa empresa = new Empresa();
		empresa.setLojaId(lojaId);
		empresa.setNomeFantasia(nomeFantasia);
		empresa.setDocumento(new Documento(documento.getTipo(), documento.getValor()));

		Endereco enderecoEntity = new Endereco();
		enderecoEntity.setLogradouro(endereco.getLogradouro());
		enderecoEntity.setNumero(endereco.getNumero());
		enderecoEntity.setBairro(endereco.getBairro());
		enderecoEntity.setCidade(endereco.getCidade());
		enderecoEntity.setEstado(endereco.getEstado());
		enderecoEntity.setCep(endereco.getCep());
		empresa.setEndereco(enderecoEntity);

		Set<Telefone> telefoneEntities = new LinkedHashSet<>();
		for (TelefoneRequest telefone : telefones) {
			telefoneEntities.add(new Telefone(telefone.getDdd(), telefone.getNumero()));
		}
		empresa.setTelefones(telefoneEntities);
		return empresa;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
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
