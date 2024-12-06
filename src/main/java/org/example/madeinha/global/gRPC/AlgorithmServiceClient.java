package org.example.madeinha.global.gRPC;

import io.grpc.ManagedChannel;
import org.springframework.stereotype.Component;

import static org.example.madeinha.global.gRPC.AlgorithmServiceOuterClass.*;

@Component
public class AlgorithmServiceClient {

    private final AlgorithmServiceGrpc.AlgorithmServiceBlockingStub stub;

    public AlgorithmServiceClient(ManagedChannel channel) {
        this.stub = AlgorithmServiceGrpc.newBlockingStub(channel);
    }

    public String dbScan(String inputJson) {
        AlgorithmRequest request = AlgorithmRequest.newBuilder()
                .setJsonInput(inputJson)
                .build();
        AlgorithmResponse response = stub.dbScan(request);
        return response.getJsonOutput();
    }

    public String convexHull(String inputJson) {
        AlgorithmRequest request = AlgorithmRequest.newBuilder()
                .setJsonInput(inputJson)
                .build();

        AlgorithmResponse response = stub.convexHull(request);
        return response.getJsonOutput();
    }

    public String pointInPolygon(String inputJson) {
        AlgorithmRequest request = AlgorithmRequest.newBuilder()
                .setJsonInput(inputJson)
                .build();

        AlgorithmResponse response = stub.pointInPolygon(request);
        return response.getJsonOutput();
    }
}