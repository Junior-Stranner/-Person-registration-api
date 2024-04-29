package br.com.judev.register.web.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice  // Aplicado para criar um conselho global para controladores, permitindo tratamento centralizado de exceções
public class ApiExceptionHandler {
    

    @ExceptionHandler(EntityNotFoundException.class) // Indica que este método lida com a exceção EntityNotFoundException
    public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex, HttpServletRequest request) {
        // Log de erro para depuração e diagnóstico
        log.error("Api Error - ", ex); // Registra o erro para fins de depuração

        // Cria uma resposta HTTP com código de status 404 (Not Found)
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // Define o status HTTP como 404
                .contentType(MediaType.APPLICATION_JSON) // Define o tipo de conteúdo como JSON
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage())); // Corpo da resposta com detalhes do erro
    }

}
