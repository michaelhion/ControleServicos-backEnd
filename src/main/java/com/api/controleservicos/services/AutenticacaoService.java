package com.api.controleservicos.services;

import com.api.controleservicos.dto.DadosAutenticacao;
import com.api.controleservicos.models.Usuario;
import com.api.controleservicos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public void novo(DadosAutenticacao userDto) {
        UserDetails jaExiste = repository.findByLogin(userDto.login());
        if(jaExiste!=null){
            throw new RuntimeException("Usuário já existe");
        }else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Usuario user = new Usuario();
            user.setLogin(userDto.login());
            user.setSenha(encoder.encode(userDto.senha()));
            repository.save(user);
        }


    }
}