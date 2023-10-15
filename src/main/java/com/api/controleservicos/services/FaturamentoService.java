package com.api.controleservicos.services;

import com.api.controleservicos.dto.DadosFaturamento;
import com.api.controleservicos.models.Faturamento;
import com.api.controleservicos.repositories.FaturamentoRepository;
import com.api.controleservicos.utils.EscreveExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FaturamentoService {
    @Autowired
    private FaturamentoRepository repo;

    @Autowired
    private EscreveExcel excel;
    public void save(Faturamento fat) {
        repo.save(fat);
    }

    public Optional<Faturamento> bustaPorId(Long id){
        return repo.findById(id);
    }

    public List<Faturamento> listar() {
        return repo.findAll();
    }

    public void alterar(Faturamento fat) {
        if(repo.existsById(fat.getId())){
            repo.save(fat);
        }
    }

    public void deletar(Long id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
        }
    }

    public List<DadosFaturamento> dadosFaturamento(){
        return repo.dadosFaturamento();
    }

    public List<DadosFaturamento> listaFaturamentoPorDatas(Date dtInicio, Date dtFim){
        return repo.dadosFaturamento();
    }

    public byte[] geraExcel() {
        byte[] retorno = null;
        try {
            retorno = excel.gerarExcel(repo.dadosFaturamento());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retorno;
    }
}
