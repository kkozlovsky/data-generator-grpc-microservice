package ru.kerporation.datageneratorgrpcmicroservice.service;

import ru.kerporation.datageneratorgrpcmicroservice.model.test.DataTestOptions;

public interface TestDataService {

    void sendMessages(final DataTestOptions testOptions);

}