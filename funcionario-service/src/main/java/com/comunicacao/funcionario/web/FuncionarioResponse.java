package com.comunicacao.funcionario.web;

import java.util.List;
import java.util.stream.Collectors;

import com.comunicacao.funcionario.domain.Funcionario;

public class FuncionarioResponse {

	private Long id;
	private Long lojaId;
	private String nome;
	private String perfil;
	private FuncionarioDocumentoResponse documento;
	private FuncionarioEnderecoResponse endereco;
	private List<FuncionarioTelefoneResponse> telefones;

	public static FuncionarioResponse from(Funcionario funcionario) {
		FuncionarioResponse response = new FuncionarioResponse();
		response.id = funcionario.getId();
		response.lojaId = funcionario.getLojaId();
		response.nome = funcionario.getNome();
		response.perfil = funcionario.getPerfil();

		FuncionarioDocumentoResponse documento = new FuncionarioDocumentoResponse();
		documento.setTipo(funcionario.getDocumento().getTipo());
		documento.setValor(funcionario.getDocumento().getValor());
		response.documento = documento;

		FuncionarioEnderecoResponse endereco = new FuncionarioEnderecoResponse();
		endereco.setLogradouro(funcionario.getEndereco().getLogradouro());
		endereco.setNumero(funcionario.getEndereco().getNumero());
		endereco.setBairro(funcionario.getEndereco().getBairro());
		endereco.setCidade(funcionario.getEndereco().getCidade());
		endereco.setEstado(funcionario.getEndereco().getEstado());
		endereco.setCep(funcionario.getEndereco().getCep());
		response.endereco = endereco;

		response.telefones = funcionario.getTelefones().stream().map(telefone -> {
			FuncionarioTelefoneResponse telefoneResponse = new FuncionarioTelefoneResponse();
			telefoneResponse.setDdd(telefone.getDdd());
			telefoneResponse.setNumero(telefone.getNumero());
			return telefoneResponse;
		}).collect(Collectors.toList());

		return response;
	}

	public Long getId() {
		return id;
	}

	public Long getLojaId() {
		return lojaId;
	}

	public String getNome() {
		return nome;
	}

	public String getPerfil() {
		return perfil;
	}

	public FuncionarioDocumentoResponse getDocumento() {
		return documento;
	}

	public FuncionarioEnderecoResponse getEndereco() {
		return endereco;
	}

	public List<FuncionarioTelefoneResponse> getTelefones() {
		return telefones;
	}

	public static class FuncionarioDocumentoResponse {
		private String tipo;
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

	public static class FuncionarioEnderecoResponse {
		private String logradouro;
		private String numero;
		private String bairro;
		private String cidade;
		private String estado;
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

	public static class FuncionarioTelefoneResponse {
		private String ddd;
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
