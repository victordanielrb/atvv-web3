package com.comunicacao.venda.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendas")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "loja_id", nullable = false)
	private Long lojaId;

	@Column(name = "func_id", nullable = false)
	private Long funcId;

	@Column(name = "cli_id", nullable = false)
	private Long cliId;

	@Column(name = "item_id", nullable = false)
	private Long itemId;

	@Column(name = "item_nome", nullable = false)
	private String itemNome;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_item", nullable = false)
	private TipoVendaItem tipoItem;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal valor;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(name = "data_venda", nullable = false)
	private LocalDate dataVenda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLojaId() {
		return lojaId;
	}

	public void setLojaId(Long lojaId) {
		this.lojaId = lojaId;
	}

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public Long getCliId() {
		return cliId;
	}

	public void setCliId(Long cliId) {
		this.cliId = cliId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemNome() {
		return itemNome;
	}

	public void setItemNome(String itemNome) {
		this.itemNome = itemNome;
	}

	public TipoVendaItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(TipoVendaItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}
}
