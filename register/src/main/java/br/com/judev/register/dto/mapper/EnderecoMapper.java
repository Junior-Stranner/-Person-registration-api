package br.com.judev.register.dto.mapper;

import br.com.judev.register.domain.Endereco.Endereco;
import br.com.judev.register.dto.EnderecoDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EnderecoMapper {

    public static Endereco toEndereco(EnderecoDto dto){
        return new ModelMapper().map(dto,Endereco.class);
    }
    public static EnderecoDto ToDto(Endereco entity){
        return new ModelMapper().map(entity, EnderecoDto.class);
    }

    public static List<EnderecoDto> toListDto(List<Endereco> enderecos) {
        return enderecos.stream().map(endereco -> ToDto(endereco)).collect(Collectors.toList());
    }
}
