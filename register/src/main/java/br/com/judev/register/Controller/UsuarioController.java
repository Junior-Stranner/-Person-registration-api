package br.com.judev.register.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/persons")
@Tag(name = "Persons" , description = "endpoints for Persons")
public class UsuarioController {
    
}
