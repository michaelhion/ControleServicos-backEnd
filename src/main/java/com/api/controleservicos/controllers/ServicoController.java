package com.api.controleservicos.controllers;


import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/servicos")
@SecurityRequirement(name = "bearer-key")
public class ServicoController {
	
	@Autowired
	private ServicoService serv;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody Servico servico, UriComponentsBuilder uriBuilder){
		this.serv.adicionar(servico);

		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(servico.getId()).toUri();

		return ResponseEntity.created(uri).body(new Servico());
	}

	@GetMapping
	public ResponseEntity<List<Servico>> listar(){
		var retorno =  serv.listar();
		return ResponseEntity.ok(retorno);
	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody Servico servico) {

		serv.alterar(servico);

		return ResponseEntity.ok(servico);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id){
		serv.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity buscarPorId(@PathVariable Long id){
		var servico1 = serv.buscarPorId(id);

		return ResponseEntity.ok(servico1);
	}

	@GetMapping("/selecionaIdENomeServicoPorId/{id}")
	public ResponseEntity selecionaIdENomeServicoPorId(@PathVariable Long id){
		var servico = serv.dadosServicoPorId(id);
		return ResponseEntity.ok(servico);
	}

	@GetMapping("/selecionaIdENomeCliente")
	public ResponseEntity selecionaIdENomeCliente(){
		var servico = serv.dadosServico();
		return ResponseEntity.ok(servico);
	}
}
