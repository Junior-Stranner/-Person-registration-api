package br.com.judev.register.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.judev.register.domain.person.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    
}
