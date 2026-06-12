package com.comunicacao.catalogo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunicacao.catalogo.repository.ItemCatalogoRepository;
import com.comunicacao.catalogo.repository.VeiculoCatalogoRepository;

@SpringBootTest
class CatalogoServiceTests {

	@Autowired
	private ItemCatalogoRepository itemRepository;

	@Autowired
	private VeiculoCatalogoRepository veiculoRepository;

	@Test
	void deveSubirComCatalogoSeparadoDoEstoque() {
		assertThat(itemRepository.findByLojaId(1L)).isNotEmpty();
		assertThat(veiculoRepository.findByLojaId(1L)).isNotEmpty();
	}
}
