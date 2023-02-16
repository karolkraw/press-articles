package com.example.pressarticles;

import com.example.pressarticles.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // (scanBasePackages={"services","repositories", "entities", "controllers"})

public class PressArticlesApplication {




    public static void main(String[] args) {
        SpringApplication.run(PressArticlesApplication.class, args);
    }

    /*@EventListener(ApplicationReadyEvent.class)
    public void init() {
        Author author = new Author();
        author.setFirstName("firstname");
        author.setLastName("lastname");
        authorRepository.save(author);
    }*/
}
