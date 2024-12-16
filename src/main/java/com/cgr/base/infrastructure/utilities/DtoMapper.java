package com.cgr.base.infrastructure.utilities;

import java.util.List;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DtoMapper() {

    }

    public <T> T convertToDto(Object entity, Class<T> dtoClass) {
        return this.modelMapper.map(entity, dtoClass);
    }

    public <T, E> List<T> convertToListDto(Iterable<E> entities, Class<T> dtoClass) {
        return StreamSupport.stream(entities.spliterator(), false).map(entity -> convertToDto(entity, dtoClass))
                .toList();
    }

}
