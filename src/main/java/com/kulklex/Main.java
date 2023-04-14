package com.kulklex;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SpringBootApplication
@RestController
public class Main {
    public static void main (String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public String greetings() {
        return "Hello World testing";
    }

    @GetMapping("/greet")
    public GreetResponse greet() {
        GreetResponse response =  new GreetResponse(
                "Greetings....",
                List.of("Java", "Typescript", "Python"),
                new Person("Hassan", 25, 90_000)
        );

        return response;
    }

    record Person (String name, int age, double savings){};
    record GreetResponse (String greet, List<String> FavProgLang, Person person){}
}
