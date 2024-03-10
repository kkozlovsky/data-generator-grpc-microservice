package ru.kerporation.datageneratorgrpcmicroservice.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kerporation.datageneratorgrpcmicroservice.model.MeasurementType;

@NoArgsConstructor
@Getter
@Setter
public class DataTestOptionsDto {

    private int delayInSeconds;
    private MeasurementType[] measurementTypes;
}