package com.carto.server;

import com.carto.server.model.CartoUser;
import com.carto.server.modules.user.CartoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

@SpringBootApplication
@RequiredArgsConstructor
public class CartoServerApplication {

    private final CartoUserRepository cartoUserRepository;

    @Bean
    public Function<String, CartoUser> fetchUser() {
        return (this.cartoUserRepository::findByFirebaseId);
    }

    public static void main(String[] args) {
        SpringApplication.run(CartoServerApplication.class, args);
    }

}
