package br.com.judev.register.Service;

import java.util.List;

import br.com.judev.register.domain.Endereco.Endereco;
import br.com.judev.register.repository.EnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.judev.register.repository.PessoaRepository;
import br.com.judev.register.web.exeption.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import br.com.judev.register.domain.person.Pessoa;

@Service // Anotação para indicar que esta classe é um serviço gerenciado pelo Spring
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository; // Repositório para operações no banco de dados
    private final  EnderecoRepository enderecoRepository;


    // Construtor do serviço que recebe uma instância do repositório para injeção de dependência
   

    @Transactional(readOnly = true) // Indica que este método não modifica o banco de dados
    public List<Pessoa> listar() {
        return pessoaRepository.findAll(); // Retorna todas as pessoas do banco de dados
    }

    @Transactional // Garante a consistência das operações de banco de dados
    public Pessoa salvarPessoa(Pessoa pessoa) {
        validarDadosObrigatorios(pessoa); // Valida se os campos obrigatórios estão preenchidos
        return pessoaRepository.save(pessoa); // Salva a pessoa no banco de dados
    }

    // Método privado para validar campos obrigatórios de uma pessoa
    private void validarDadosObrigatorios(Pessoa pessoa) {
        if (pessoa.getNomeCompleto() == null || pessoa.getNomeCompleto().isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório."); // Lança exceção se o nome estiver ausente
        }
        if (pessoa.getDataNascimento() == null) {
            throw new IllegalArgumentException("A data de nascimento é obrigatória."); // Lança exceção se a data de nascimento estiver ausente
        }
    }

    @Transactional(readOnly = true) // Indica que o método é apenas para leitura, sem modificação do banco de dados
    public Pessoa buscarPorId(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Pessoa com ID " + id + " não encontrada.")); // Lança exceção se o ID não for encontrado
        return pessoa; // Retorna a pessoa encontrada
    }

    @Transactional // Indica que este método modifica o banco de dados
    public Pessoa alterarPessoa(Pessoa pessoa, Long id) {

        // Busca a pessoa existente pelo ID e lança exceção se não for encontrada
        Pessoa pessoaExistente = pessoaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Pessoa com ID " + id + " não encontrada."));

             validarDadosObrigatorios(pessoa); // Certifica-se de que a pessoa tem dados válidos

        // Atualiza os campos da pessoa existente com os novos dados
        pessoaExistente.setNomeCompleto(pessoa.getNomeCompleto());
        pessoaExistente.setDataNascimento(pessoa.getDataNascimento());

        return pessoaRepository.save(pessoaExistente); // Salva as alterações no banco de dados
    }

    @Transactional // Indica que este método modifica o banco de dados
    public void deletarPessoa(Long id) {
        // Verifica se a pessoa existe
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Pessoa com ID " + id + " não encontrada ou já deletada.")); // Lança exceção se não for encontrada
        // Verifica se há endereços associados à pessoa
        List<Endereco> enderecos = enderecoRepository.findByPessoaId(id);
        if (!enderecos.isEmpty()) {
            throw new EntityNotFoundException("Não é possível deletar a pessoa com ID " + id + ". Ela ainda é referenciada por endereços.");
        }
        // Se não houver referências, exclui a pessoa
        pessoaRepository.delete(pessoa); // Exclui a pessoa do banco de dados
    }

}