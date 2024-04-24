package br.com.judev.register.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.judev.register.domain.user.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
