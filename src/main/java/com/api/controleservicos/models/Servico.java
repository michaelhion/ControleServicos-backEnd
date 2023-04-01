package com.api.controleservicos.models;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tbl_servico")
public class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nomeServico;
	private String nomeCliente;
	private BigDecimal valor;
	@Temporal(TemporalType.DATE)
	private Calendar data;
	private BigDecimal vlComissao;
	private BigDecimal vlLiquido;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public BigDecimal getVlComissao() {
		return vlComissao;
	}
	public void setVlComissao(BigDecimal vlComissao) {
		this.vlComissao = vlComissao;
	}
	public BigDecimal getVlLiquido() {
		return vlLiquido;
	}
	public void setVlLiquido(BigDecimal vlLiquido) {
		this.vlLiquido = vlLiquido;
	}
	
	@Override
	public String toString() {
		
		return "\n nomeServico: " + this.nomeServico + "\n" + " nomeCliente: " + this.nomeCliente + "\n" + " valor: " + this.valor + "\n" + " data: " + this.data + "\n" + " valor: " + this.valor + "\n" + " vlcomissao: "+ this.vlComissao + "\n" + "vlLiquido: " + this.vlLiquido ;
	}
}
