package br.com.judev.register.Service;

import java.util.List;

import br.com.judev.register.domain.person.Pessoa;
import br.com.judev.register.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.judev.register.repository.EnderecoRepository;
import br.com.judev.register.domain.Endereco.Endereco;

@Service
public class EnderecoService {
    
    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }


    @Transactional(readOnly = true) // Esta anotação indica que o método é apenas de leitura no banco de dados
    public List<Endereco> listar(){
        return enderecoRepository.findAll();
        
    }

    public Endereco salvarEndereco(Endereco endereco) {
        validarDadosObrigatoriosEnderecos(endereco);
        return enderecoRepository.save(endereco);
    }
    private void validarDadosObrigatoriosEnderecos(Endereco endereco) {

        if (endereco.getLogradouro() == null || endereco.getLogradouro().isBlank()) {
            throw new IllegalArgumentException("O logradouro é obrigatório.");
        }
        if (endereco.getCep() == null || endereco.getCep().isBlank()) {
            throw new IllegalArgumentException("O CEP é obrigatório.");
        }
        if (endereco.getNumero() == 0) {
            throw new IllegalArgumentException("O número é obrigatório.");
        }
        if (endereco.getCidade() == null || endereco.getCidade().isBlank()) {
            throw new IllegalArgumentException("A cidade é obrigatória.");
        }
        if (endereco.getEstado() == null || endereco.getEstado().isBlank()) {
            throw new IllegalArgumentException("O estado é obrigatório.");
        }
    }

    public Endereco buscarPorId(Long id) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Endereco com ID " + id + " não encontrada."));
        validarDadosObrigatoriosEnderecos(endereco);
        return endereco;
    }

    @Transactional // Indica que este método modifica o banco de dados
    public Endereco alterarEndereco(Endereco endereco, Long id, Long pessoaId) {
        validarDadosObrigatoriosEnderecos(endereco); // Certifica-se de que o endereço tem dados válidos

        // Busca o endereço existente pelo ID e lança exceção se não for encontrado
        Endereco enderecoExistente = enderecoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Endereço com ID " + id + " não encontrado."));

        // Se o ID da pessoa for fornecido, busca no repositório e lança exceção se não for encontrada
        if (pessoaId != null) {
            Pessoa pessoaExistente = pessoaRepository.findById(pessoaId).orElseThrow(() ->
                    new IllegalArgumentException("Pessoa com ID " + pessoaId + " não encontrada."));
            enderecoExistente.setPessoa(pessoaExistente);
        }
        // Atualiza os campos do endereço com os novos dados
        enderecoExistente.setLogradouro(endereco.getLogradouro());
        enderecoExistente.setCep(endereco.getCep());
        enderecoExistente.setNumero(endereco.getNumero());
        enderecoExistente.setCidade(endereco.getCidade());

        return enderecoRepository.save(enderecoExistente); // Salva as alterações no banco de dados
    }

    @Transactional // Indica que este método modifica o banco de dados
    public Endereco alterarPessoaNoEndereco(Long enderecoId, Long pessoaId) {
        // Busca o endereço pelo ID e lança exceção se não for encontrado
        Endereco enderecoExistente = enderecoRepository.findById(enderecoId).orElseThrow(() ->
                new IllegalArgumentException("Endereço com ID " + enderecoId + " não encontrado."));

        // Busca a pessoa pelo ID e lança exceção se não for encontrada
        Pessoa pessoaExistente = pessoaRepository.findById(pessoaId).orElseThrow(() ->
                new IllegalArgumentException("Pessoa com ID " + pessoaId + " não encontrada."));

        // Atualiza a pessoa associada ao endereço
        enderecoExistente.setPessoa(pessoaExistente);

        return enderecoRepository.save(enderecoExistente); // Salva as alterações no banco de dados
    }



    @Transactional // Indica que este método modifica o banco de dados
    public void deletarEndereco(Long id) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Pessoa com ID " + id +"não encontrada.")); // Lança exceção se o ID não for encontrado

        enderecoRepository.delete(endereco); // Exclui a pessoa do banco de dados
    }
}
