package com.comunicacao.catalogo.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "catalogo_veiculos")
public class VeiculoCatalogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "loja_id", nullable = false)
	private Long lojaId;

	@Column(nullable = false)
	private String placa;

	@Column(nullable = false)
	private String modelo;

	@Column(nullable = false)
	private String marca;

	@Column(name = "cliente_nome", nullable = false)
	private String clienteNome;

	@Column(name = "data_atendimento", nullable = false)
	private LocalDate dataAtendimento;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public Long getLojaId() { return lojaId; }
	public void setLojaId(Long lojaId) { this.lojaId = lojaId; }
	public String getPlaca() { return placa; }
	public void setPlaca(String placa) { this.placa = placa; }
	public String getModelo() { return modelo; }
	public void setModelo(String modelo) { this.modelo = modelo; }
	public String getMarca() { return marca; }
	public void setMarca(String marca) { this.marca = marca; }
	public String getClienteNome() { return clienteNome; }
	public void setClienteNome(String clienteNome) { this.clienteNome = clienteNome; }
	public LocalDate getDataAtendimento() { return dataAtendimento; }
	public void setDataAtendimento(LocalDate dataAtendimento) { this.dataAtendimento = dataAtendimento; }
}
