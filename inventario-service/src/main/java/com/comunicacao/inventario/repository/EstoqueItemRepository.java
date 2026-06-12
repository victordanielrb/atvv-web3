package com.comunicacao.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.inventario.domain.EstoqueItem;

public interface EstoqueItemRepository extends JpaRepository<EstoqueItem, Long> {
	List<EstoqueItem> findByLojaId(Long lojaId);
}
