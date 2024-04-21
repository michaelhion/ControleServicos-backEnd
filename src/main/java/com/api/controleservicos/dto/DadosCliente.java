package com.api.controleservicos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record DadosCliente(

        @Schema(name = "Nome", description = "ARmazena o nome do usuário")
        String nome
) {
}
