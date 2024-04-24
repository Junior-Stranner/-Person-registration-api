package br.com.judev.register.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.judev.register.Service.UsuarioService;
import br.com.judev.register.domain.user.Usuario;
import br.com.judev.register.dto.UsuarioCreateDto;
import br.com.judev.register.dto.UsuarioResponseDto;
import br.com.judev.register.dto.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/persons")
@Tag(name = "Persons" , description = "endpoints for Persons")
public class UsuarioController {
    

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    
    @Operation(summary = "Criar um novo usuário", description = "Recurso para criar um novo usuário",
    responses = {
            @ApiResponse(responseCode = "200", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Usuário e-mail já cadastrado no sistema",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada invalidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))
    })
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })//Swagger exige isso  "produces = MediaType.APPLICATION_JSON_VALUE"
    public ResponseEntity<UsuarioResponseDto> criar(@RequestBody UsuarioCreateDto dto){
        Usuario usuario = UsuarioMapper.toUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.ToDto(usuario));
        
    }
}
