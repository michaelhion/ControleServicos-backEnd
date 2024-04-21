package com.api.controleservicos.controllers;

import com.api.controleservicos.dto.DadosAutenticacao;
import com.api.controleservicos.dto.DadosTokenJwt;
import com.api.controleservicos.models.Usuario;
import com.api.controleservicos.security.TokenService;
import com.api.controleservicos.services.AutenticacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Tag(name = "Autenticação")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    AutenticacaoService service;

    @PostMapping
    @Operation(summary = "Gera token")
    public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados){
        var authenticationtoken = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var authentication = manager.authenticate(authenticationtoken);
        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJwt(tokenJwt));
    }

    @PostMapping("novo")
    @Operation(summary = "Cadastra usuário")
    public ResponseEntity novo(@RequestBody DadosAutenticacao dados){
        service.novo(dados);
        return ResponseEntity.ok().build();
    }
}