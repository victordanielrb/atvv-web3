package com.comunicacao.empresa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.comunicacao.empresa.domain.Documento;
import com.comunicacao.empresa.domain.Empresa;
import com.comunicacao.empresa.domain.Endereco;
import com.comunicacao.empresa.domain.Telefone;
import com.comunicacao.empresa.repository.EmpresaRepository;

@DataJpaTest
class EmpresaRepositoryTests {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Test
	void devePersistirEmbeddablesEElementCollection() {
		Empresa empresa = new Empresa();
		empresa.setLojaId(10L);
		empresa.setNomeFantasia("Loja Centro");
		empresa.setDocumento(new Documento("CNPJ", "12345678000199"));

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua A");
		endereco.setNumero("100");
		endereco.setBairro("Centro");
		endereco.setCidade("Sao Paulo");
		endereco.setEstado("SP");
		endereco.setCep("01000-000");
		empresa.setEndereco(endereco);

		LinkedHashSet<Telefone> telefones = new LinkedHashSet<>();
		telefones.add(new Telefone("11", "999999999"));
		telefones.add(new Telefone("11", "988888888"));
		empresa.setTelefones(telefones);

		empresaRepository.saveAndFlush(empresa);

		Empresa encontrada = empresaRepository.findByLojaId(10L).orElseThrow();
		assertThat(encontrada.getDocumento().getValor()).isEqualTo("12345678000199");
		assertThat(encontrada.getEndereco().getCidade()).isEqualTo("Sao Paulo");
		assertThat(encontrada.getTelefones()).hasSize(2);
	}
}
