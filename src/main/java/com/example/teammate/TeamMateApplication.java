package com.example.teammate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TeamMateApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamMateApplication.class, args);
    }

}
