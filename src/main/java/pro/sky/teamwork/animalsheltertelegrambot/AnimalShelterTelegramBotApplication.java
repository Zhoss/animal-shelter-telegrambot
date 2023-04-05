package pro.sky.teamwork.animalsheltertelegrambot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition
public class AnimalShelterTelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalShelterTelegramBotApplication.class, args);
    }

}
