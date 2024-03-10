package ru.kerporation.datageneratorgrpcmicroservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kerporation.datageneratorgrpcmicroservice.model.Data;
import ru.kerporation.datageneratorgrpcmicroservice.model.test.DataTestOptions;
import ru.kerporation.datageneratorgrpcmicroservice.service.GRPCDataService;
import ru.kerporation.datageneratorgrpcmicroservice.service.TestDataService;
import ru.kerporation.datageneratorgrpcmicroservice.web.dto.DataDto;
import ru.kerporation.datageneratorgrpcmicroservice.web.dto.DataTestOptionsDto;
import ru.kerporation.datageneratorgrpcmicroservice.web.mapper.DataMapper;
import ru.kerporation.datageneratorgrpcmicroservice.web.mapper.DataTestOptionsMapper;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {

    private final GRPCDataService GRPCDataService;
    private final TestDataService testDataService;

    private final DataMapper dataMapper;
    private final DataTestOptionsMapper dataTestOptionsMapper;

    @PostMapping("/send")
    public void send(@RequestBody final DataDto dataDto) {
        final Data data = dataMapper.toEntity(dataDto);
        GRPCDataService.send(data);
    }

    @PostMapping("/test/send")
    public void testSend(@RequestBody final DataTestOptionsDto testOptionsDto) {
        final DataTestOptions testOptions = dataTestOptionsMapper.toEntity(testOptionsDto);
        testDataService.sendMessages(testOptions);
    }
}