package com.api.controleservicos.controllers;

import com.api.controleservicos.models.Cliente;
import com.api.controleservicos.models.Servico;
import com.api.controleservicos.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Cliente")
public class ClienteController {

    @Autowired
    ClienteService serv;

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastra cliente")
    public ResponseEntity cadastrar(@RequestBody Cliente cliente, UriComponentsBuilder uriBuilder){
        this.serv.adicionar(cliente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new Servico());
    }

    @GetMapping
    @Operation(summary = "Lista cliente")
    public ResponseEntity<List<Cliente>> listar(){
        var retorno =  serv.listar();
        return ResponseEntity.ok(retorno);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Edita cliente")
    public ResponseEntity atualizar(@RequestBody Cliente cliente) {
        serv.alterar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Exclui cliente")
    public ResponseEntity excluir(@PathVariable Long id){
        serv.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista cliente por id")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        var servico1 = serv.buscarPorId(id);

        return ResponseEntity.ok(servico1);
    }

    @GetMapping("/selecionaIdENomeClientePorId/{id}")
    public ResponseEntity selecionaIdENomeClientePorId(@PathVariable Long id){
        var cliente = serv.dadosClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/selecionaIdENomeCliente")
    public ResponseEntity selecionaIdENomeCliente(){
        var cliente = serv.dadosCliente();
        return ResponseEntity.ok(cliente);
    }
}
