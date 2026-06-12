package com.comunicacao.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.catalogo.domain.ItemCatalogo;

public interface ItemCatalogoRepository extends JpaRepository<ItemCatalogo, Long> {
	List<ItemCatalogo> findByLojaId(Long lojaId);
}
