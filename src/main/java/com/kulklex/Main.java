package com.kulklex;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
    public static void main (String[] args) {
        SpringApplication.run(Main.class, args);
    }

    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getCustomers(){
        return  customerRepository.findAll();
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
