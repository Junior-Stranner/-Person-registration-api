package br.com.judev.register.domain.person;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pessoas")
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeCompleto;
    private LocalDate dataNascimento;

  /*  @OneToMany(mappedBy = "pessoa") // Indica o lado inverso do relacionamento
    private List<Endereco> enderecos; // Uma lista de endereços*/

}
