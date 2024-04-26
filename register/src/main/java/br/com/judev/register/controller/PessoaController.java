package br.com.judev.register.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.judev.register.Service.PessoaService;
import br.com.judev.register.domain.person.Pessoa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // Define este controlador como um controlador RESTful
@RequestMapping("/api/v1/pessoas") // Define a base para os endpoints
@Tag(name = "Persons", description = "endpoints for managing persons")
public class PessoaController {

    private final PessoaService pessoaService; // Injeção de dependência do serviço de pessoas

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService; // Inicializa o serviço
    }

    @Operation(summary = "Criar uma nova pessoa", description = "Endpoint para criar uma nova pessoa",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
                    @ApiResponse(responseCode = "409", description = "Pessoa com as mesmas informações já cadastrada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
                    @ApiResponse(responseCode = "422", description = "Recurso não processado por dados inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class)))
            })
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }) // Endpoint para criar uma pessoa
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        Pessoa pessoaCriada = pessoaService.salvarPessoa(pessoa); // Salva uma nova pessoa
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaCriada); // Retorna HTTP 201 para sucesso
    }

    @Operation(summary = "Listar todas as pessoas cadastradas", description = "Endpoint para listar todas as pessoas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de pessoas cadastradas",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Pessoa.class))))
            })
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE }) // Endpoint para listar todas as pessoas
    public ResponseEntity<List<Pessoa>> listarTodos() {
        List<Pessoa> pessoas = pessoaService.listar(); // Retorna todas as pessoas do banco de dados
        return ResponseEntity.ok(pessoas); // HTTP 200 para resposta bem-sucedida
    }

    @Operation(summary = "Buscar uma pessoa pelo ID", description = "Endpoint para buscar uma pessoa pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
                    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
            })
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }) // Endpoint para buscar pelo ID
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.buscarPorId(id); // Busca uma pessoa pelo ID
        return ResponseEntity.ok(pessoa); // HTTP 200 para sucesso
    }

    @Operation(summary = "Alterar uma pessoa", description = "Endpoint para alterar uma pessoa pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pessoa alterada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation =  Pessoa.class))),
                    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
            })
    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }) // Endpoint para alterar pelo ID
    public ResponseEntity<Pessoa> alterarPessoa(@RequestBody Pessoa pessoa, @PathVariable Long id) {
        Pessoa pessoaAlterada = pessoaService.alterarPessoa(pessoa, id); // Altera a pessoa pelo ID
        return ResponseEntity.ok(pessoaAlterada); // HTTP 200 para sucesso
    }

    @Operation(summary = "Deletar uma pessoa", description = "Endpoint para deletar uma pessoa pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
            })
    @DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }) // Endpoint para deletar pelo ID
    public ResponseEntity<String> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id); // Exclui a pessoa pelo ID
        return ResponseEntity.ok("Pessoa deletada com sucesso!"); // Retorna mensagem de sucesso
    }
}