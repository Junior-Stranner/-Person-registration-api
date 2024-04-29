package br.com.judev.register.Service;

import java.util.List;
import java.util.Optional;

import br.com.judev.register.domain.person.Pessoa;
import br.com.judev.register.dto.EnderecoDto;
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

    @Transactional(readOnly = true) // Esta anotação indica que o método é apenas de leitura no banco de dados
    public Pessoa buscarPessoaPorId(Long pessoaId) {
        return pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa com ID " + pessoaId + " não encontrada."));
    }
    // Exemplo de uso para obter o ID da pessoa e associá-lo ao endereço
    public Endereco salvarEndereco(Endereco endereco) {
        // Primeiro, obtenha a pessoa pelo ID
          Pessoa pessoa = buscarPessoaPorId(endereco.getPessoa().getId());
        // Associe a pessoa ao endereço
         endereco.setPessoa(pessoa);
        // Agora salve o endereço
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

    // Método para buscar um endereço pelo ID
    public Endereco buscarPorId(Long id) {
        // Tenta encontrar o endereço pelo ID no repositório
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Endereço com ID " + id + " não encontrado.")); // Lança exceção se não encontrar

        // Valida se o endereço possui dados obrigatórios
        validarDadosObrigatoriosEnderecos(endereco);

        // Retorna o endereço encontrado
        return endereco;
    }

    @Transactional // Indica que este método modifica o banco de dados
    public Endereco alterarEndereco(EnderecoDto dto, Long id ) {

        // Busca o endereço existente pelo ID no repositório
        Endereco enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço com ID " + id + " não encontrado.")); // Se não encontrar, lança exceção

        // Primeiro, obtenha a pessoa pelo ID
        Pessoa pessoa = pessoaRepository.findById(enderecoExistente.getPessoa().getId())
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));
        enderecoExistente.setPessoa(pessoa);

        // Valida se o endereço a ser alterado possui dados obrigatórios=
        validarDadosObrigatoriosEnderecos(enderecoExistente);

        // Atualiza campos com base no DTO
        enderecoExistente.setLogradouro(dto.getLogradouro());
        enderecoExistente.setCep(dto.getCep());
        enderecoExistente.setNumero(dto.getNumero());
        enderecoExistente.setCidade(dto.getCidade());
        enderecoExistente.setEstado(dto.getEstado());
        enderecoExistente.setPessoa(dto.getPessoa());
        // Atualiza outras propriedades conforme necessário

        return enderecoRepository.save(enderecoExistente); // Salva as alterações
    }

    @Transactional // Indica que este método modifica o banco de dados
    public void deletarEndereco(Long id) {

        Endereco  endereco = enderecoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Enderecos com ID " + id +"não encontrada.")); // Lança exceção se o ID não for encontrado
        enderecoRepository.delete(endereco); // Exclui a pessoa do banco de dados
    }
}
