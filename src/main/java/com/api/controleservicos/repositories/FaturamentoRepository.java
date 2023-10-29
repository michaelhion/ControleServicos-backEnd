package com.api.controleservicos.repositories;

import com.api.controleservicos.dto.DadosFaturamento;
import com.api.controleservicos.models.Faturamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FaturamentoRepository extends JpaRepository<Faturamento,Long> {

    @Query( """
            SELECT new com.api.controleservicos.dto.DadosFaturamento(f.id,c.nomeCompleto, s.nomeServico,s.valor,f.data)
            FROM Faturamento f
            join Servico s on s.id = f.servicoId
            join Cliente c on c.id = f.clienteId
            """)
    List<DadosFaturamento> dadosFaturamento();

    @Query(
            """
            Select new com.api.controleservicos.dto.DadosFaturamento(f.id,c.nomeCompleto, s.nomeServico,s.valor,f.data)
            FROM Faturamento f
            join Servico s on s.id = f.servicoId
            join Cliente c on c.id = f.clienteId
            where f.data >= :dtInicio and f.data <= :dtFim
            """
    )
    List<DadosFaturamento> listaFaturamentoPorDatas(@Param("dtInicio") Date dtInicio,@Param("dtFim") Date dtFim);
}
