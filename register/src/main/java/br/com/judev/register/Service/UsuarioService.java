package br.com.judev.register.Service;

import org.springframework.stereotype.Service;


import br.com.judev.register.Repository.UsuarioRepository;
import br.com.judev.register.domain.user.Usuario;

@Service
public class UsuarioService {

    private  UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) throws IllegalArgumentException{
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario) {

        // Validações para o usuário
        if (usuario.getNomeCompleto() == null || usuario.getNomeCompleto().isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        if (usuario.getEndereco() == null || usuario.getEndereco().isBlank()) {
            throw new IllegalArgumentException("O endereço é obrigatório.");

        } else if(usuario.getLogradouro() == null || usuario.getLogradouro().isBlank()){

            throw new IllegalArgumentException("Logradouro é obrigatório.");
        }

        if (usuario.getCidade() == null || usuario.getCidade().isBlank()) {
            throw new IllegalArgumentException("O nome da Cidade é obrigatório.");
        } 

        if (usuario.getCidade() == null || usuario.getCidade().isBlank()) {
            throw new IllegalArgumentException("O nome da Cidade é obrigatório.");
        } 

        verificaCep(usuario.getCep());

        return usuarioRepository.save(usuario);
    }

    public String verificaCep(int cep) {
        if (cep <= 0) {
            throw new IllegalArgumentException("O CEP é obrigatório.");
        } else {
            return "O CEP é " + cep;
        }
    }

}
