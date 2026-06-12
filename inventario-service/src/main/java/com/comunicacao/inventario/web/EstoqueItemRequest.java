package com.comunicacao.inventario.web;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.comunicacao.inventario.domain.EstoqueItem;

public class EstoqueItemRequest {
	@NotNull
	private Long itemId;
	@NotBlank
	private String nome;
	@NotNull
	@Min(0)
	private Integer quantidade;

	public EstoqueItem toEntity(Long lojaId) {
		EstoqueItem item = new EstoqueItem();
		item.setLojaId(lojaId);
		item.setItemId(itemId);
		item.setNome(nome);
		item.setQuantidade(quantidade);
		return item;
	}

	public Long getItemId() { return itemId; }
	public void setItemId(Long itemId) { this.itemId = itemId; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public Integer getQuantidade() { return quantidade; }
	public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}
