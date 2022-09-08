package com.zulvit.userDatabaseSpring.service;

import com.zulvit.userDatabaseSpring.database.CustomerRepository;
import com.zulvit.userDatabaseSpring.exception.CustomerNotFoundException;
import com.zulvit.userDatabaseSpring.exception.CustomerVoidException;
import com.zulvit.userDatabaseSpring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    //database
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll() throws CustomerVoidException {
        List<Customer> list = new ArrayList<>();
        Iterable<Customer> allMessages = this.customerRepository.findAll();
        allMessages.forEach(list::add);
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new CustomerVoidException("There are no messages in the database yet");
        }
    }

    public Optional<Customer> getFromId(long id) throws CustomerNotFoundException {
        Optional<Customer> messageObj = this.customerRepository.findById(id);
        if (messageObj.isPresent()) {
            this.customerRepository.findAllById(Collections.singleton(id));
            return messageObj;
        } else {
            throw new CustomerNotFoundException("There is no message with this ID.");
        }
    }

    public void postMessage(Customer customer) {
        this.customerRepository.save(customer);
    }

    public void deleteMessage(long id) {
        this.customerRepository.deleteById(id);
    }
}
