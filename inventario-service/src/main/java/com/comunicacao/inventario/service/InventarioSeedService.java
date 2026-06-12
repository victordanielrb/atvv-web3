package com.comunicacao.inventario.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.comunicacao.inventario.domain.EstoqueItem;
import com.comunicacao.inventario.repository.EstoqueItemRepository;

@Service
public class InventarioSeedService {

	private final EstoqueItemRepository repository;

	public InventarioSeedService(EstoqueItemRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	void seed() {
		if (repository.count() > 0) {
			return;
		}

		EstoqueItem item = new EstoqueItem();
		item.setLojaId(1L);
		item.setItemId(2L);
		item.setNome("Filtro de ar");
		item.setQuantidade(17);
		repository.saveAll(List.of(item));
	}
}
