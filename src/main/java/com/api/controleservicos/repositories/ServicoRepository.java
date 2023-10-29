package com.api.controleservicos.repositories;

import java.math.BigDecimal;
import java.util.List;

import com.api.controleservicos.dto.DadosCliente;
import com.api.controleservicos.dto.DadosServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.controleservicos.models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{

    @Query("select new com.api.controleservicos.dto.DadosServico(s.id,s.nomeServico) from Servico s where s.id = :id")
    DadosServico selecionaIdeNomePorId(@Param("id")Long id);

    @Query("select new com.api.controleservicos.dto.DadosServico(s.id,s.nomeServico) from Servico s")
    List<DadosServico> selecionaIdeNome();
}
