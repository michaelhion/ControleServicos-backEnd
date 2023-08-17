package com.api.controleservicos.services;

import com.api.controleservicos.dto.DadosCliente;
import com.api.controleservicos.models.Cliente;
import com.api.controleservicos.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repo;

    public void adicionar(Cliente cliente){
        repo.save(cliente);
    }

    public void alterar(Cliente cliente){
        if (repo.existsById(cliente.getId())) {
            repo.save(cliente);
        }
    }

    public List<Cliente> listar(){
        return  repo.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id){
        return repo.findById(id);
    }

    public void excluir(Long id){
        this.repo.deleteById(id);
    }

    public void excluirTudo(){
        this.repo.deleteAll();
    }

    public DadosCliente dadosClientePorId(Long id){
        return repo.selecionaIdeNomePorId(id);
    }

    public List<DadosCliente> dadosCliente(){
        return repo.selecionaIdeNome();
    }
}
