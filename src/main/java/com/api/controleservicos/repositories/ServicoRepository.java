package com.api.controleservicos.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.controleservicos.models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{
	@Query("SELECT sum(s.valor) as total,sum(s.vlComissao) as totalComissao FROM Servico s")
	BigDecimal total();
	
	@Query("SELECT sum(s.vlLiquido) as totalLiquido FROM Servico s")
	BigDecimal totalLiquido();
	
	@Query("SELECT sum(s.vlComissao) as totalComissao FROM Servico s")
	BigDecimal totalComissao();
	
	@Query("SELECT s FROM Servico s WHERE EXTRACT(MONTH FROM s.data) = :mes")
	List<Servico> buscarPorMes(@Param("mes")int nrMes);
}
