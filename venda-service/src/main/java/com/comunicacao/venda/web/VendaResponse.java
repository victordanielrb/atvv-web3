package com.comunicacao.venda.web;

import com.comunicacao.venda.domain.Venda;

public class VendaResponse {

	private Long id;
	private Long lojaId;
	private Long funcId;
	private Long cliId;
	private Long itemId;
	private String itemNome;
	private String tipoItem;
	private java.math.BigDecimal valor;
	private Integer quantidade;
	private java.time.LocalDate dataVenda;

	public static VendaResponse from(Venda venda) {
		VendaResponse response = new VendaResponse();
		response.id = venda.getId();
		response.lojaId = venda.getLojaId();
		response.funcId = venda.getFuncId();
		response.cliId = venda.getCliId();
		response.itemId = venda.getItemId();
		response.itemNome = venda.getItemNome();
		response.tipoItem = venda.getTipoItem().name();
		response.valor = venda.getValor();
		response.quantidade = venda.getQuantidade();
		response.dataVenda = venda.getDataVenda();
		return response;
	}

	public Long getId() {
		return id;
	}

	public Long getLojaId() {
		return lojaId;
	}

	public Long getFuncId() {
		return funcId;
	}

	public Long getCliId() {
		return cliId;
	}

	public Long getItemId() {
		return itemId;
	}

	public String getItemNome() {
		return itemNome;
	}

	public String getTipoItem() {
		return tipoItem;
	}

	public java.math.BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public java.time.LocalDate getDataVenda() {
		return dataVenda;
	}
}
