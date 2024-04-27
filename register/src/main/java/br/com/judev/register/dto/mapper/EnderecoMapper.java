package br.com.judev.register.dto.mapper;

import br.com.judev.register.domain.Endereco.Endereco;
import br.com.judev.register.dto.AlterarEnderecoDto;
import org.modelmapper.ModelMapper;

public class EnderecoMapper {

    public static Endereco toEndereco(AlterarEnderecoDto dto){
        return new ModelMapper().map(dto,Endereco.class);
    }
    public static AlterarEnderecoDto ToDto(Endereco entity){
        return new ModelMapper().map(entity,AlterarEnderecoDto.class);
    }

}
