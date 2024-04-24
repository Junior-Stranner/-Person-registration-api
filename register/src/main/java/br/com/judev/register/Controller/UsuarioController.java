package br.com.judev.register.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.judev.register.Service.UsuarioService;
import br.com.judev.register.domain.user.Usuario;
import br.com.judev.register.dto.UsuarioCreateDto;
import br.com.judev.register.dto.UsuarioResponseDto;
import br.com.judev.register.dto.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/persons")
@Tag(name = "Persons" , description = "endpoints for Persons")
public class UsuarioController {
    

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    public ResponseEntity<UsuarioResponseDto> criar(@RequestBody UsuarioCreateDto dto){
        Usuario usuario = UsuarioMapper.toUsuario(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.ToDto(usuario));
        
    }
}
