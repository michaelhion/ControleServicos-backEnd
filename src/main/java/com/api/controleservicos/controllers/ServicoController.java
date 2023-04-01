package com.api.controleservicos.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.controleservicos.models.Servico;
import com.api.controleservicos.services.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
	
	@Autowired
	private ServicoService serv;
	
	@GetMapping
	public ResponseEntity<List<Servico>> listar(){
		return new ResponseEntity<List<Servico>>(this.serv.listar(),HttpStatus.OK);
	}
	
	@GetMapping("/mes/{nrMes}")
	public ResponseEntity<List<Servico>> filtroMes(@PathVariable(value = "nrMes") int nrMes){
		return new ResponseEntity<List<Servico>>(this.serv.buscarMes(nrMes),HttpStatus.OK);
	}
	
	@GetMapping("/total")
	public ResponseEntity<?> total(){
		return new ResponseEntity<>(this.serv.total(),HttpStatus.OK);
	}
	
	@GetMapping("/totalcomissao")
	public ResponseEntity<?> totalComissao(){
		return new ResponseEntity<>(this.serv.totalComissao(),HttpStatus.OK);
	}
	
	@GetMapping("/totalliquido")
	public ResponseEntity<?> totalLiquido(){
		return new ResponseEntity<>(this.serv.totalLiquido(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(this.serv.buscarPorId(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Servico> adicionar(@RequestBody Servico servico){
		return new ResponseEntity<Servico>(this.serv.adicionarOuAlterar(servico),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable(value = "id") Long id){
		this.serv.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<Servico> alterar(@PathVariable(value = "id") Long id,@RequestBody Servico servico){
		return new ResponseEntity<Servico>(this.serv.adicionarOuAlterar(servico),HttpStatus.OK);
	}
}
