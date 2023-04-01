package com.api.controleservicos.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.controleservicos.exceptions.NotFoundException;
import com.api.controleservicos.models.Servico;
import com.api.controleservicos.repositories.ServicoRepository;

@Service
public class ServicoService {


	@Autowired
	private ServicoRepository repo;
	
	private BigDecimal calculo(BigDecimal valor,boolean isVlLiquido) {
		BigDecimal retorno;
		
		if (isVlLiquido) {
			retorno = valor.multiply(new BigDecimal("0.7"));
		}else {
			retorno = valor.multiply(new BigDecimal("0.3"));
		}
		return retorno;
	}
	
	
	
	public Servico adicionarOuAlterar(Servico serv) {
		if (serv.getId()!=null) {
			this.repo.save(serv);
		}else {
			serv.setVlComissao(this.calculo(serv.getValor(), false));
			serv.setVlLiquido(this.calculo(serv.getValor(), true));
			this.repo.save(serv);
		}
		return serv;	
	}
	
	public List<Servico> listar(){
		return this.repo.findAll();
	}
	
	public Servico buscarPorId(Long id){
		return this.repo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}
	
	public List<Servico> buscarMes(int id){
		return this.repo.buscarPorMes(id);
	}
	
	public BigDecimal total(){
		return this.repo.total();
	}
	
	public BigDecimal totalComissao(){
		return this.repo.totalComissao();
	}
	
	public BigDecimal totalLiquido(){
		return this.repo.totalLiquido();
	}
	
	public void excluir(Long id){
		this.repo.deleteById(id);
	}
	
	public void excluirTudo(){
		this.repo.deleteAll();
	}
}
