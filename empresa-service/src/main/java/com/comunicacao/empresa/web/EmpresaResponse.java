package com.comunicacao.empresa.web;

import java.util.Set;
import java.util.stream.Collectors;

import com.comunicacao.empresa.domain.Empresa;

public class EmpresaResponse {

	private Long id;
	private Long lojaId;
	private String nomeFantasia;
	private EmpresaRequest.DocumentoRequest documento;
	private EmpresaRequest.EnderecoRequest endereco;
	private Set<EmpresaRequest.TelefoneRequest> telefones;

	public static EmpresaResponse from(Empresa empresa) {
		EmpresaResponse response = new EmpresaResponse();
		response.id = empresa.getId();
		response.lojaId = empresa.getLojaId();
		response.nomeFantasia = empresa.getNomeFantasia();

		EmpresaRequest.DocumentoRequest documento = new EmpresaRequest.DocumentoRequest();
		documento.setTipo(empresa.getDocumento().getTipo());
		documento.setValor(empresa.getDocumento().getValor());
		response.documento = documento;

		EmpresaRequest.EnderecoRequest endereco = new EmpresaRequest.EnderecoRequest();
		endereco.setLogradouro(empresa.getEndereco().getLogradouro());
		endereco.setNumero(empresa.getEndereco().getNumero());
		endereco.setBairro(empresa.getEndereco().getBairro());
		endereco.setCidade(empresa.getEndereco().getCidade());
		endereco.setEstado(empresa.getEndereco().getEstado());
		endereco.setCep(empresa.getEndereco().getCep());
		response.endereco = endereco;

		response.telefones = empresa.getTelefones().stream().map(telefone -> {
			EmpresaRequest.TelefoneRequest telefoneRequest = new EmpresaRequest.TelefoneRequest();
			telefoneRequest.setDdd(telefone.getDdd());
			telefoneRequest.setNumero(telefone.getNumero());
			return telefoneRequest;
		}).collect(Collectors.toSet());

		return response;
	}

	public Long getId() {
		return id;
	}

	public Long getLojaId() {
		return lojaId;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public EmpresaRequest.DocumentoRequest getDocumento() {
		return documento;
	}

	public EmpresaRequest.EnderecoRequest getEndereco() {
		return endereco;
	}

	public Set<EmpresaRequest.TelefoneRequest> getTelefones() {
		return telefones;
	}
}
