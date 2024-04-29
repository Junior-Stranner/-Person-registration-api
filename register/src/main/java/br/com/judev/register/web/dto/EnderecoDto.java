package br.com.judev.register.web.dto;

import br.com.judev.register.domain.person.Pessoa;

public class EnderecoDto {
    private Long id; // ID do endereço
    private Pessoa Pessoa; // ID da pessoa associada
    private String logradouro;
    private String cep;
    private int numero;
    private String cidade;
    private String estado;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public br.com.judev.register.domain.person.Pessoa getPessoa() {
        return Pessoa;
    }

    public void setPessoa(br.com.judev.register.domain.person.Pessoa pessoa) {
        Pessoa = pessoa;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

