package com.api.controleservicos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record DadosAutenticacao(
        @Schema(name = "Login", description = "string que representa o nome do usuário")
        String login,
        @Schema(name = "Senha", description = "armazena a senha")
        String senha) {
}
