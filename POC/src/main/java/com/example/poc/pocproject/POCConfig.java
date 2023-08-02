package com.example.poc.pocproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class POCConfig {

    @Bean
    CommandLineRunner commandLineRunner(POCRepository repository) {
        return args -> {
          POCProject Fuji =  new POCProject(
                "Fuji",
                "fujisyusuke@gmail.com",
                LocalDate.of(2000, AUGUST, 20)
            );
            POCProject ryoma =  new POCProject(
                    "ryoma",
                    "ryomaechizen@gmail.com",
                    LocalDate.of(2000, AUGUST, 20)
            );

            repository.saveAll(
                    List.of(Fuji, ryoma)
            );
    };
    }
}
