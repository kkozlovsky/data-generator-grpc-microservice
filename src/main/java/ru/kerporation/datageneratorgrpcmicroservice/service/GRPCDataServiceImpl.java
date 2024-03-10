package ru.kerporation.datageneratorgrpcmicroservice.service;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.kerporation.datageneratorgrpcmicroservice.model.Data;
import ru.kerporation.grpccommon.DataServerGrpc;
import ru.kerporation.grpccommon.GRPCData;
import ru.kerporation.grpccommon.MeasurementType;

import java.time.ZoneOffset;
import java.util.List;

@Service
public class GRPCDataServiceImpl implements GRPCDataService {

    @GrpcClient(value = "data-generator-blocking")
    private DataServerGrpc.DataServerBlockingStub blockingStub;

    @GrpcClient(value = "data-generator-async")
    private DataServerGrpc.DataServerStub asyncStub;

    @Override
    public void send(final Data data) {
        final GRPCData request = generateGRPCDataRequest(data);
        blockingStub.addData(request);
    }


    @Override
    public void send(final List<Data> data) {
        final StreamObserver<Empty> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(Empty empty) {
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onCompleted() {
            }
        };

        final StreamObserver<GRPCData> requestObserver = asyncStub.addStreamOfData(responseObserver);
        for (final Data d : data) {
            final GRPCData request = generateGRPCDataRequest(d);
            requestObserver.onNext(request);
        }
        requestObserver.onCompleted();
    }

    private GRPCData generateGRPCDataRequest(final Data data) {
        return GRPCData.newBuilder()
                .setSensorId(data.getSensorId())
                .setTimestamp(Timestamp.newBuilder().setSeconds(data.getTimestamp().toEpochSecond(ZoneOffset.UTC)).build())
                .setMeasurementType(MeasurementType.valueOf(data.getMeasurementType().name()))
                .setMeasurement(data.getMeasurement())
                .build();
    }

}
