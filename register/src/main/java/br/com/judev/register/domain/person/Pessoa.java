package br.com.judev.register.domain.person;

import java.time.LocalDate;
import java.util.List;

import br.com.judev.register.domain.Endereco.Endereco;
import jakarta.persistence.*;

@Entity
@Table(name="pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeCompleto;
    private LocalDate dataNascimento;

  /*  @OneToMany(mappedBy = "pessoa") // Indica o lado inverso do relacionamento
    private List<Endereco> enderecos; // Uma lista de endereços*/


    public Pessoa(String nomeCompleto, LocalDate dataNascimento, String endereco) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
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

}
