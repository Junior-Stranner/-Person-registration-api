package br.com.judev.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.judev.register.domain.Endereco.Endereco;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco , Long>{

    List<Endereco> findByPessoaId(Long id);
}
