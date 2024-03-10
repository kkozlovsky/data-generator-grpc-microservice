package ru.kerporation.datageneratorgrpcmicroservice.web.mapper;

import org.mapstruct.Mapper;
import ru.kerporation.datageneratorgrpcmicroservice.model.test.DataTestOptions;
import ru.kerporation.datageneratorgrpcmicroservice.web.dto.DataTestOptionsDto;

@Mapper(componentModel = "spring")
public interface DataTestOptionsMapper extends Mappable<DataTestOptions, DataTestOptionsDto> {
}
