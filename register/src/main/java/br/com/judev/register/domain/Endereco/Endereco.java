package br.com.judev.register.domain.Endereco;

import br.com.judev.register.domain.person.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enderecos")
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class Endereco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String logradouro;
    private String cep;
    private int numero;
    private String cidade;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "pessoa_id") // Use o nome da coluna para associação
    private Pessoa pessoa; // Relacionamento com Pessoa

    
}
