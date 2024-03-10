package ru.kerporation.datageneratorgrpcmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kerporation.datageneratorgrpcmicroservice.model.Data;
import ru.kerporation.datageneratorgrpcmicroservice.model.MeasurementType;
import ru.kerporation.datageneratorgrpcmicroservice.model.test.DataTestOptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TestDataServiceImpl implements TestDataService {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final GRPCDataService GRPCDataService;

    @Value("${push.batch-size}")
    private int batchSize;

    @Override
    public void sendMessages(final DataTestOptions testOptions) {
        final List<Data> dataBatch = new ArrayList<>();
        if (testOptions.getMeasurementTypes().length > 0) {
            executorService.scheduleAtFixedRate(
                    () -> {
                        final Data data = new Data();
                        data.setSensorId((long) getRandomNumber(1, 10));
                        data.setMeasurement(getRandomNumber(15, 20));
                        data.setMeasurementType(getRandomMeasurement(testOptions.getMeasurementTypes()));
                        data.setTimestamp(LocalDateTime.now());
                        dataBatch.add(data);
                        if (dataBatch.size() == batchSize) {
                            GRPCDataService.send(dataBatch);
                            dataBatch.clear();
                        }
                    },
                    0,
                    testOptions.getDelayInSeconds(),
                    TimeUnit.SECONDS
            );
        }
    }

    private double getRandomNumber(final double min,
                                   final double max) {
        return (Math.random() * (max - min)) + min;
    }

    private MeasurementType getRandomMeasurement(final MeasurementType[] measurementTypes) {
        int randomTypeId = (int) (Math.random() * measurementTypes.length);
        return measurementTypes[randomTypeId];
    }

}