package ru.kerporation.datageneratorgrpcmicroservice.service;

import ru.kerporation.datageneratorgrpcmicroservice.model.Data;

import java.util.List;

public interface GRPCDataService {

    void send(final Data data);

    void send(final List<Data> data);
}