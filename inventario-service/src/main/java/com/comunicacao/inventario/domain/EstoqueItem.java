package com.comunicacao.inventario.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estoque_itens")
public class EstoqueItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "loja_id", nullable = false)
	private Long lojaId;

	@Column(name = "item_id", nullable = false)
	private Long itemId;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private Integer quantidade;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public Long getLojaId() { return lojaId; }
	public void setLojaId(Long lojaId) { this.lojaId = lojaId; }
	public Long getItemId() { return itemId; }
	public void setItemId(Long itemId) { this.itemId = itemId; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public Integer getQuantidade() { return quantidade; }
	public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}
