package com.api.controleservicos.controllers;

import com.api.controleservicos.dto.DadosFaturamento;
import com.api.controleservicos.models.Faturamento;
import com.api.controleservicos.models.Servico;
import com.api.controleservicos.repositories.FaturamentoRepository;
import com.api.controleservicos.services.FaturamentoService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/faturamento")
public class FaturamentoController {

    @Autowired
    private FaturamentoService serv;

    @PostMapping
    public ResponseEntity<?> adicionaServico(@RequestBody Faturamento fat, UriComponentsBuilder uriBuilder){
        serv.save(fat);
        var uri = uriBuilder.path("/faturamento/{id}").buildAndExpand(serv.bustaPorId (fat.getId())).toUri();
        return ResponseEntity.created(uri).body(new Faturamento());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Faturamento>> listar(){
        var retorno = serv.listar();
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("/listarFaturamento")
    public ResponseEntity<List<DadosFaturamento>> listarFaturamento(){
        var retorno = serv.dadosFaturamento();
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("/listarFaturamento/{dtInicio}/{dtFim}")
    public ResponseEntity<List<DadosFaturamento>> listarFaturamentoPorData(/*@PathVariable Date dtInicio,@PathVariable Date dtFim*/
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dtInicio,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dtFim) {
        var retorno = serv.listaFaturamentoPorDatas(dtInicio,dtFim);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("/listaPorId{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        var fat1 = serv.bustaPorId(id);

        return ResponseEntity.ok(fat1);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterar(@RequestBody Faturamento fat){
        serv.alterar(fat);

        return ResponseEntity.ok(fat);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        serv.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
