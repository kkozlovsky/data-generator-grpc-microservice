package ru.kerporation.datageneratorgrpcmicroservice.web.mapper;

import org.mapstruct.Mapper;
import ru.kerporation.datageneratorgrpcmicroservice.model.Data;
import ru.kerporation.datageneratorgrpcmicroservice.web.dto.DataDto;

@Mapper(componentModel = "spring")
public interface DataMapper extends Mappable<Data, DataDto> {
}
