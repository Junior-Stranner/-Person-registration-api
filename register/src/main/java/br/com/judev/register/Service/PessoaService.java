package br.com.judev.register.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.judev.register.repository.PessoaRepository;
import br.com.judev.register.domain.person.Pessoa;

@Service
public class PessoaService {

    private  PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) throws IllegalArgumentException{
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true) // Esta anotação indica que o método é apenas de leitura no banco de dados
    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
        
    }

    @Transactional // Garante a consistência das operações de banco de dados
    public Pessoa salvarPessoa(Pessoa pessoa) {

        validarDadosObrigatorios(pessoa);
        return pessoaRepository.save(pessoa);
    }
    private void validarDadosObrigatorios(Pessoa pessoa) {

        if (pessoa.getNomeCompleto() == null || pessoa.getNomeCompleto().isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (pessoa.getDataNascimento() == null) {
            throw new IllegalArgumentException("A data de nascimento é obrigatória.");
        }
        if (pessoa.getEndereco() == null || pessoa.getEndereco().isBlank()) {
                throw new IllegalArgumentException("Endereço é obrigatório");
            }
        }

    public Pessoa buscarPorId(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Pessoa com ID " + id + " não encontrada."));
        validarDadosObrigatorios(pessoa);
        return pessoa;
    }
}
