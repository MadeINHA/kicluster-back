package org.example.madeinha.global.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Bean
    public ManagedChannel grpcManagedChannel() {
        return ManagedChannelBuilder
                .forAddress("알고리즘 서버 주소", 50051)
                .usePlaintext()
                .build();
    }
}
