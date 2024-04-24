package br.com.judev.register.Service;

import org.springframework.stereotype.Service;

import br.com.judev.register.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    private  UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    
}
