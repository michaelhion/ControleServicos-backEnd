package com.api.controleservicos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record DadosCliente(

        @Schema(name = "Id", description = "Id do cliente gerado automaticamente pelo sistema")
        Long id,
        @Schema(name = "Nome", description = "ARmazena o nome do usuário")
        String nome
) {
}
