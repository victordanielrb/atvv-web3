package com.comunicacao.inventario;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunicacao.inventario.repository.EstoqueItemRepository;

@SpringBootTest
class InventarioServiceTests {

	@Autowired
	private EstoqueItemRepository repository;

	@Test
	void deveSubirComServicoDeEstoqueSeparado() {
		assertThat(repository.findByLojaId(1L)).isNotEmpty();
	}
}
