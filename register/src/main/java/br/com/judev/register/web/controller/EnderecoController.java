package br.com.judev.register.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.judev.register.Service.EnderecoService;
import br.com.judev.register.domain.Endereco.Endereco;
import br.com.judev.register.web.dto.EnderecoDto;
import br.com.judev.register.web.dto.mapper.EnderecoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController // Indica que este é um controlador REST
@RequestMapping("/api/v1/enderecos") // Define a base do endpoint
@Tag(name = "Endereços", description = "Endpoints para manipular endereços") // Anotação para Swagger/OpenAPI
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService; // Injeção do serviço de endereços

   

    @Operation(summary = "Criar um novo endereço", description = "Endpoint para criar um novo endereço",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class))),
                    @ApiResponse(responseCode = "409", description = "Endereço com informações duplicadas",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class))),
                    @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class)))
            })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // Define que o endpoint consome e produz JSON
    public ResponseEntity<EnderecoDto> criar(@RequestBody EnderecoDto dto ) {
        Endereco novoEndereco = enderecoService.salvarEndereco(EnderecoMapper.toEndereco(dto)); // Salva um novo endereço
        return ResponseEntity.status(HttpStatus.CREATED).body(EnderecoMapper.ToDto(novoEndereco)); // Retorna HTTP 201 para sucesso
    }

    @Operation(summary = "Listar todos os endereços cadastrados", description = "Lista todos os endereços cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de todos os endereços cadastrados",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Endereco.class))))
            })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) // Endpoint para listar todos os endereços
    public ResponseEntity<List<EnderecoDto>> listarTodos() {
        List<Endereco> enderecos = enderecoService.listar(); // Retorna todos os endereços do banco de dados
        return ResponseEntity.ok(EnderecoMapper.toListDto(enderecos)); // HTTP 200 para resposta bem-sucedida
    }

    @Operation(summary = "Buscar um endereço pelo ID", description = "Endpoint para buscar um endereço pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class))),
                    @ApiResponse(responseCode = "404", description = "Endereço não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class))),
            })
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }) // Endpoint para buscar pelo ID
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        Endereco endereco = enderecoService.buscarPorId(id); // Busca um endereço pelo ID
        return ResponseEntity.ok(endereco); // HTTP 200 para sucesso
    }

    @Operation(summary = "Alterar um endereço", description = "Endpoint para alterar um endereço pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Endereço alterado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class))),
                    @ApiResponse(responseCode = "404", description = "Endereço não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Endereco.class))),
            })
    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }) // Endpoint para alterar pelo ID
    public ResponseEntity<EnderecoDto> alterarEndereco(@RequestBody EnderecoDto dto, @PathVariable Long id ) {
        Endereco enderecoAlterado = enderecoService.alterarEndereco(dto,id); // Altera o endereço pelo ID
        return ResponseEntity.ok(EnderecoMapper.ToDto(enderecoAlterado)); // HTTP 200 para sucesso
    }

    @Operation(summary = "Deletar um endereço", description = "Endpoint para deletar um endereço pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Endereço deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
            })
    @DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }) // Endpoint para deletar pelo ID
    public ResponseEntity<String> deletarEndereco(@PathVariable Long id) {
        enderecoService.deletarEndereco(id); // Exclui o endereço pelo ID
        return ResponseEntity.ok("Endereço deletado com sucesso!"); // Retorna mensagem de sucesso
    }
}
