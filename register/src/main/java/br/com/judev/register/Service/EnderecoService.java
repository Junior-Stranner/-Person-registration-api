package br.com.judev.register.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.judev.register.Repository.EnderecoRepository;
import br.com.judev.register.domain.Endereco.Endereco;

@Service
public class EnderecoService {
    
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
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

}
