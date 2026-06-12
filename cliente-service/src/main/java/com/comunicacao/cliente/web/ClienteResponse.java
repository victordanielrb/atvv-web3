package com.comunicacao.cliente.web;

import java.util.List;
import java.util.stream.Collectors;

import com.comunicacao.cliente.domain.Cliente;

public class ClienteResponse {

	private Long id;
	private Long lojaId;
	private String nome;
	private ClienteRequest.DocumentoRequest documento;
	private ClienteRequest.EnderecoRequest endereco;
	private List<ClienteRequest.TelefoneRequest> telefones;

	public static ClienteResponse from(Cliente cliente) {
		ClienteResponse response = new ClienteResponse();
		response.id = cliente.getId();
		response.lojaId = cliente.getLojaId();
		response.nome = cliente.getNome();

		ClienteRequest.DocumentoRequest documento = new ClienteRequest.DocumentoRequest();
		documento.setTipo(cliente.getDocumento().getTipo());
		documento.setValor(cliente.getDocumento().getValor());
		response.documento = documento;

		ClienteRequest.EnderecoRequest endereco = new ClienteRequest.EnderecoRequest();
		endereco.setLogradouro(cliente.getEndereco().getLogradouro());
		endereco.setNumero(cliente.getEndereco().getNumero());
		endereco.setBairro(cliente.getEndereco().getBairro());
		endereco.setCidade(cliente.getEndereco().getCidade());
		endereco.setEstado(cliente.getEndereco().getEstado());
		endereco.setCep(cliente.getEndereco().getCep());
		response.endereco = endereco;

		response.telefones = cliente.getTelefones().stream().map(telefone -> {
			ClienteRequest.TelefoneRequest request = new ClienteRequest.TelefoneRequest();
			request.setDdd(telefone.getDdd());
			request.setNumero(telefone.getNumero());
			return request;
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

	public ClienteRequest.DocumentoRequest getDocumento() {
		return documento;
	}

	public ClienteRequest.EnderecoRequest getEndereco() {
		return endereco;
	}

	public List<ClienteRequest.TelefoneRequest> getTelefones() {
		return telefones;
	}
}
