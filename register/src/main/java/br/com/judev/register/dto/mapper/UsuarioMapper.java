package br.com.judev.register.dto.mapper;

import org.modelmapper.ModelMapper;

import br.com.judev.register.domain.user.Usuario;
import br.com.judev.register.dto.UsuarioCreateDto;
import br.com.judev.register.dto.UsuarioResponseDto;

public class UsuarioMapper {
    

    public static Usuario toUsuario(UsuarioCreateDto dto){
        return new ModelMapper().map(dto,Usuario.class);
    }
    public static UsuarioResponseDto ToDto(Usuario entity){
        return new ModelMapper().map(entity,UsuarioResponseDto.class);
    }
}
