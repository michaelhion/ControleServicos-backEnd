package com.api.controleservicos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_item_faturado")
public class ItemFaturado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "faturamento_id")
    private Faturamento faturamento;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Faturamento getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(Faturamento faturamento) {
        this.faturamento = faturamento;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
