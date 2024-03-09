
package ru.kerporation.datageneratorgrpcmicroservice.model.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kerporation.datageneratorgrpcmicroservice.model.MeasurementType;

@NoArgsConstructor
@Getter
@Setter
public class DataTestOptions {

    private int delayInSeconds;
    private MeasurementType[] measurementTypes;
}