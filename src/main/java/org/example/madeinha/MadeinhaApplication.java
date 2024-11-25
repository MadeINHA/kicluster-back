package org.example.madeinha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableRedisRepositories
public class MadeinhaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MadeinhaApplication.class, args);
    }

}
