package br.com.judev.register.web.exeption;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;

public class ErrorMessage  {
    
    // Propriedades da classe ErrorMessage
    private String path; // Caminho da requisição onde ocorreu o erro
    private String method; // Método HTTP utilizado na requisição (GET, POST, etc.)
    private int status; // Código de status HTTP (ex: 404 para "Not Found", 500 para "Internal Server Error")
    private String statusText; // Texto associado ao código de status (ex: "Not Found", "Internal Server Error")
    private String message; // Mensagem descritiva sobre o erro
    @JsonInclude(JsonInclude.Include.NON_NULL) // Incluir apenas se não for nulo
    private Map<String, String> errors; // Mapeamento de erros, normalmente campo de erro para mensagem

    // Construtor padrão (sem parâmetros)
    public ErrorMessage() {
    }

    // Construtor para criar uma mensagem de erro básica sem detalhes adicionais
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
        this.path = request.getRequestURI(); // Armazena o caminho da requisição
        this.method = request.getMethod(); // Armazena o método HTTP usado
        this.status = status.value(); // Armazena o código de status HTTP
        this.statusText = status.getReasonPhrase(); // Armazena o texto do status HTTP
        this.message = message; // Armazena a mensagem descritiva do erro
    }

    // Construtor para criar uma mensagem de erro com detalhes adicionais, incluindo erros de validação
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result) {
        this.path = request.getRequestURI(); // Armazena o caminho da requisição
        this.method = request.getMethod(); // Armazena o método HTTP usado
        this.status = status.value(); // Armazena o código de status HTTP
        this.statusText = status.getReasonPhrase(); // Armazena o texto do status HTTP
        this.message = message; // Armazena a mensagem descritiva do erro
        addErrors(result); // Adiciona erros de validação ao mapeamento de erros
    }

    // Método para adicionar erros de validação ao mapeamento de erros
    private void addErrors(BindingResult result) {
        this.errors = new HashMap<>(); // Inicializa o mapa de erros
        // Adiciona cada erro de campo do BindingResult ao mapa
        for (FieldError fieldError : result.getFieldErrors()) {
            this.errors.put(fieldError.getField(), fieldError.getDefaultMessage()); // Armazena o campo e a mensagem do erro
        }
    }
}
