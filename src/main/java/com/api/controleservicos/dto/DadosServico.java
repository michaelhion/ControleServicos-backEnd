package com.api.controleservicos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record DadosServico(
        @Schema(name = "Id", description = "Id do servico gerado automaticamente pelo sistema")
        Long id,
        @Schema(name = "Nome do servico", description = "Armazena o nome do servico")
        @NotBlank(message = "Nomo do servico não pode ser nulo")
        String nome) {
}
