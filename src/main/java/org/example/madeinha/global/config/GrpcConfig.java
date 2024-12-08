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
                .forAddress("3.36.68.148", 50051)
                .usePlaintext()
                .build();
    }
}
