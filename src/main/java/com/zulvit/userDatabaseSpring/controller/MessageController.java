package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.model.Message;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final List<Message> messages = new ArrayList<>();

    @GetMapping
    public String list() {
//        Initialize
//        messages.addAll(List.of(
//                new Message(0, "Привет"),
//                new Message(1, "Да, здарова!")
//        ));
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

    @PostMapping("/add")
    Message postMessage(@RequestBody Message message) {
        this.messages.add(message);
        return message;
    }

    @DeleteMapping("{id}")
    void deleteMessage(@PathVariable int id) {
        for (int i = 0; i < messages.size(); i++) {
            if (this.messages.get(i).getId() == id) {
                messages.remove(id);
            }
        }
    }
}
