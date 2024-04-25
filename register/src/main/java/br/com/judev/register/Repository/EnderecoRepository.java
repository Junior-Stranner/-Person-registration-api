package br.com.judev.register.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.judev.register.domain.Endereco.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco , Long>{
    
}
