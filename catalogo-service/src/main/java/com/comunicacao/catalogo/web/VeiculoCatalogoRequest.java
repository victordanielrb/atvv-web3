package com.comunicacao.catalogo.web;

import javax.validation.constraints.NotBlank;

import com.comunicacao.catalogo.domain.VeiculoCatalogo;

public class VeiculoCatalogoRequest {
	@NotBlank
	private String placa;
	@NotBlank
	private String modelo;
	@NotBlank
	private String marca;
	@NotBlank
	private String clienteNome;

	public VeiculoCatalogo toEntity(Long lojaId) {
		VeiculoCatalogo veiculo = new VeiculoCatalogo();
		veiculo.setLojaId(lojaId);
		veiculo.setPlaca(placa);
		veiculo.setModelo(modelo);
		veiculo.setMarca(marca);
		veiculo.setClienteNome(clienteNome);
		veiculo.setDataAtendimento(java.time.LocalDate.now());
		return veiculo;
	}

	public String getPlaca() { return placa; }
	public void setPlaca(String placa) { this.placa = placa; }
	public String getModelo() { return modelo; }
	public void setModelo(String modelo) { this.modelo = modelo; }
	public String getMarca() { return marca; }
	public void setMarca(String marca) { this.marca = marca; }
	public String getClienteNome() { return clienteNome; }
	public void setClienteNome(String clienteNome) { this.clienteNome = clienteNome; }
}
