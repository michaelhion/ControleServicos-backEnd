package com.api.controleservicos.dto;

import java.math.BigDecimal;
import java.util.Date;

public record DadosFaturamento(Long id,String nomeCliente, String nomeServico, BigDecimal valor, Date data) {
}
