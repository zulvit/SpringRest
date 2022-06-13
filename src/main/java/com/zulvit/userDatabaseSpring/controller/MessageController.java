package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("message")
public class MessageController {
    private List<Message> messages = new ArrayList();

    @GetMapping
    public String list() {
        messages.addAll(List.of(
                new Message(0, "Привет"),
                new Message(1, "Да, здарова!")
        ));
        return messages.toString();
    }

    @GetMapping("{id}")
    public Optional<String> getFirst(@PathVariable int id) {
        for (Message message : messages) {
            if (message.getId() == id) {
                return Optional.of(message.getMessage());
            }
        }
        return Optional.empty();
    }
}
