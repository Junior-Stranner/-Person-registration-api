package br.com.judev.register.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.judev.register.Service.EnderecoService;
import br.com.judev.register.domain.Endereco.Endereco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/enderecos")
@Tag(name = "Endereços", description = "Endpoints para manipular endereços")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Operation(summary = "Criar um novo endereço", description = "Cria um novo registro de endereço",
    responses = {
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "409", description = "Endereço com informações duplicadas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class)))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Endereco> criar(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.salvarEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    @Operation(summary = "Listar todos os endereços cadastrados", description = "Mostra uma lista de todos os endereços cadastrados",
    responses = {
            @ApiResponse(responseCode = "200", description = "Lista de todos os endereços cadastrados",
                    content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Endereco.class))))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Endereco>> listarTodos() {
        List<Endereco> enderecos = enderecoService.listar();
        return ResponseEntity.ok(enderecos);
    }
}
