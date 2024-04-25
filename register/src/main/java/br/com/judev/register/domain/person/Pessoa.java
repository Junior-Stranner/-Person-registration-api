package br.com.judev.register.domain.person;

import java.time.LocalDate;
import java.util.List;

import br.com.judev.register.domain.Endereco.Endereco;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String endereco;
  

    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> enderecos;
    

    public Pessoa(String nomeCompleto, LocalDate dataNascimento, String endereco, List<Endereco> enderecos) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.enderecos = enderecos;
    }

    public Pessoa() {
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    

}
