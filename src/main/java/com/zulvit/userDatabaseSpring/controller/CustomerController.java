package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.exception.CustomerNotFoundException;
import com.zulvit.userDatabaseSpring.exception.CustomerVoidException;
import com.zulvit.userDatabaseSpring.model.Customer;
import com.zulvit.userDatabaseSpring.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService message) {
        this.customerService = message;
    }

    @GetMapping()
    public ResponseEntity<String> getAll() {
        try {
            List<Customer> list = customerService.getAll();
            return ResponseEntity.ok("Список покупателей: \n" + list);
        } catch (CustomerVoidException e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.badRequest().body("Ещё нет ни одного пользователя.");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.badRequest().body("Произошла непредвиденная ошибка.");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getById(@PathVariable int id) {
        try {
            Optional<Customer> messageId = this.customerService.getFromId(id);
            return ResponseEntity.ok("Пользователь найден: \n" + "\"" + messageId.get().getFirstName() + "\"");
        } catch (CustomerNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.badRequest().body("Пользователь не найден.");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.badRequest().body("Произошла непредвиденная ошибка.");
        }
    }

    @PostMapping()
    public ResponseEntity<String> postCustomer(@RequestBody Customer customer) {
        try {
            customerService.postMessage(customer);
            return ResponseEntity.ok("Пользователь добавлен.");
        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
            return ResponseEntity.badRequest().body("Произошла ошибка при добавлении.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        try {
            customerService.deleteMessage(id);
            return ResponseEntity.ok("Пользователь удален.");
        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
            return ResponseEntity.badRequest().body("Произошла ошибка при удалении.");
        }
    }
}
