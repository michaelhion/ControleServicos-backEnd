package com.api.controleservicos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;

public record DadosFaturamento(
        @Schema(name = "Id", description = "Id do cliente gerado automaticamente pelo sistema")
        Long id,
        @Schema(name = "Nome do cliente", description = "Armazena o nome do cliente que recebeu o servico")
        @NotBlank(message = "Nomo do cliente não pode ser nulo")
        String nomeCliente,
        @Schema(name = "Nome do servico", description = "Armazena o nome do servico prestado")
        @NotBlank(message = "Nomo do serviço não pode ser nulo")
        String nomeServico,
        @Schema(name = "Valor do servico", description = "Armazena o valor do serviço")
        @NotBlank(message = "Valor não deve ser nulo")
        BigDecimal valor,
        @Schema(name = "Data do servico", description = "Armazena a data que o  servico foi feito")
        Date data
) {
}
