package com.comunicacao.cliente;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.comunicacao.cliente.domain.Cliente;
import com.comunicacao.cliente.domain.Documento;
import com.comunicacao.cliente.domain.Endereco;
import com.comunicacao.cliente.domain.Telefone;
import com.comunicacao.cliente.repository.ClienteRepository;

@DataJpaTest
class ClienteRepositoryTests {

	@Autowired
	private ClienteRepository clienteRepository;

	@Test
	void devePersistirClienteComTelefones() {
		Cliente cliente = new Cliente();
		cliente.setLojaId(22L);
		cliente.setNome("Maria");
		cliente.setDocumento(new Documento("CPF", "12345678900"));

		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua B");
		endereco.setNumero("200");
		endereco.setBairro("Centro");
		endereco.setCidade("Campinas");
		endereco.setEstado("SP");
		endereco.setCep("13000-000");
		cliente.setEndereco(endereco);

		LinkedHashSet<Telefone> telefones = new LinkedHashSet<>();
		telefones.add(new Telefone("19", "977777777"));
		cliente.setTelefones(telefones);

		clienteRepository.saveAndFlush(cliente);

		assertThat(clienteRepository.findByLojaId(22L)).hasSize(1);
		assertThat(clienteRepository.findByLojaId(22L).get(0).getTelefones()).hasSize(1);
	}
}
