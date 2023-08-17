package com.api.controleservicos.services;

import java.math.BigDecimal;
import java.util.List;

import com.api.controleservicos.dto.DadosCliente;
import com.api.controleservicos.dto.DadosServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.controleservicos.exceptions.NotFoundException;
import com.api.controleservicos.models.Servico;
import com.api.controleservicos.repositories.ServicoRepository;

@Service
public class ServicoService {


	@Autowired
	private ServicoRepository repo;
	public List<Servico> listar(){
		return repo.findAll();
	}

	public void adicionar(Servico servico){
		repo.save(servico);
	}

	public void alterar(Servico servico){
		if(repo.existsById(servico.getId())){
			repo.save(servico);
		}
	}

	public Servico buscarPorId(Long id){
		return repo.getReferenceById(id);
	}
	
	public void excluir(Long id){
		this.repo.deleteById(id);
	}
	
	public void excluirTudo(){
		this.repo.deleteAll();
	}

	public DadosServico dadosServicoPorId(Long id){
		return repo.selecionaIdeNomePorId(id);
	}

	public List<DadosServico> dadosServico(){
		return repo.selecionaIdeNome();
	}
}
