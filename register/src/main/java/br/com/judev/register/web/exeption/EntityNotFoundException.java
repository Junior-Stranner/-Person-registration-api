package br.com.judev.register.web.exeption; // Pacote onde a classe está localizada

public class EntityNotFoundException extends RuntimeException { // Extende RuntimeException para criar uma exceção personalizada

    // Construtor que recebe uma mensagem e a passa para a superclasse (RuntimeException)
    public EntityNotFoundException(String message) {
        super(message); // Chama o construtor da superclasse com a mensagem
    }
}
