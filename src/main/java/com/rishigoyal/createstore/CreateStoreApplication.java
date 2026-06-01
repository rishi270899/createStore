package com.rishigoyal.createstore;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreateStoreApplication {

    public static void main(String[] args) {

        // configuration of dotenv-java
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        dotenv
                .entries()
                .forEach((DotenvEntry entry) ->
                        System.setProperty(entry.getKey(), entry.getValue())
                );

        SpringApplication.run(CreateStoreApplication.class, args);
    }
}
