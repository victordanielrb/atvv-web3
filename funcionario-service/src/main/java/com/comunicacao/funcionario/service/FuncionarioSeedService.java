package com.comunicacao.funcionario.service;

import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.comunicacao.funcionario.domain.Documento;
import com.comunicacao.funcionario.domain.Endereco;
import com.comunicacao.funcionario.domain.Funcionario;
import com.comunicacao.funcionario.domain.Telefone;
import com.comunicacao.funcionario.repository.FuncionarioRepository;

@Service
public class FuncionarioSeedService {

	private final FuncionarioRepository repository;

	public FuncionarioSeedService(FuncionarioRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	void seed() {
		if (repository.count() > 0) {
			return;
		}

		Funcionario admin = new Funcionario();
		admin.setLojaId(1L);
		admin.setNome("Ana Souza");
		admin.setPerfil("ADMIN");
		admin.setDocumento(new Documento("CPF", "11122233344"));
		admin.setEndereco(endereco("Rua Central", "10", "Centro", "Sao Paulo", "SP", "01000-000"));
		admin.setTelefones(telefones("11", "999900001"));

		Funcionario consultor = new Funcionario();
		consultor.setLojaId(1L);
		consultor.setNome("Bruno Lima");
		consultor.setPerfil("FUNC");
		consultor.setDocumento(new Documento("CPF", "22233344455"));
		consultor.setEndereco(endereco("Avenida Norte", "20", "Jardins", "Sao Paulo", "SP", "02000-000"));
		consultor.setTelefones(telefones("11", "999900002", "11", "988880002"));

		repository.saveAll(List.of(admin, consultor));
	}

	private Endereco endereco(String logradouro, String numero, String bairro, String cidade, String estado, String cep) {
		Endereco endereco = new Endereco();
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		endereco.setCep(cep);
		return endereco;
	}

	private java.util.Set<Telefone> telefones(String... values) {
		LinkedHashSet<Telefone> telefones = new LinkedHashSet<>();
		for (int i = 0; i < values.length; i += 2) {
			telefones.add(new Telefone(values[i], values[i + 1]));
		}
		return telefones;
	}
}
