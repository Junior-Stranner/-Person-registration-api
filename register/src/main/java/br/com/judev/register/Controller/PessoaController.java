package br.com.judev.register.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.judev.register.Service.PessoaService;
import br.com.judev.register.domain.person.Pessoa;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/pessoas")
@Tag(name = "Persons" , description = "endpoints for Persons")
public class PessoaController {
    

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    
    @Operation(summary = "Criar um novo pessoa", description = "Recurso para criar um novo pessoa",
    responses = {
            @ApiResponse(responseCode = "200", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
            @ApiResponse(responseCode = "409", description = "Pessoa com as mesma informações já cadastrado no sistema",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
            @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada invalidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class)))
    })
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })//Swagger exige isso  "produces = MediaType.APPLICATION_JSON_VALUE"
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
        
    }

    @Operation(summary = "Listar todos as Pessoas cadastrados", description = "mostre a lista de todas as pessoas",
    responses = {
            @ApiResponse(responseCode = "200", description = "Lista com todos as Pessoas cadastrados",
                    content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Pessoa.class)))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class)))
    })
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })//Swagger exige isso  "produces = MediaType.APPLICATION_JSON_VALUE"
    public ResponseEntity<List<Pessoa>> listarTodos(){
        List<Pessoa> pessoas = pessoaService.listar();
        return ResponseEntity.ok(pessoas);
    }
}
