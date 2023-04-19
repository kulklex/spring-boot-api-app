package com.kulklex;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
    public static void main (String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getCustomers(){
        return  customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ) {}

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }


    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
    }

    @PatchMapping("{customerId}")
    public void updateCustomer(@PathVariable Integer id, NewCustomerRequest newCustomer) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customer.get().setName(newCustomer.name());
            customer.get().setEmail(newCustomer.email());
            customer.get().setAge(newCustomer.age());
        }
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

    record Person (String name, int age, double savings){}
    record GreetResponse (String greet, List<String> FavProgLang, Person person){}
}
