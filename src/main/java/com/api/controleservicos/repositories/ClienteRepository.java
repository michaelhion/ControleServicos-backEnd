package com.api.controleservicos.repositories;

import com.api.controleservicos.dto.DadosCliente;
import com.api.controleservicos.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query("select new com.api.controleservicos.dto.DadosCliente(c.id,c.nomeCompleto) from Cliente c where c.id = :id")
    DadosCliente selecionaIdeNomePorId(@Param("id")Long id);

    @Query("select new com.api.controleservicos.dto.DadosCliente(c.id,c.nomeCompleto) from Cliente c")
    List<DadosCliente> selecionaIdeNome();


}
