package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.model.Message;
import com.zulvit.userDatabaseSpring.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService message) {
        this.messageService = message;
    }

    @GetMapping("")
    public List<Message> list() {
        return messageService.list();
    }

    @GetMapping("{id}")
    public Optional<String> getFirst(@PathVariable int id) {
        return messageService.getMessageId(id);
    }

    @PostMapping("add")
    public void postMessage(@RequestBody Message message) {
        messageService.postMessage(message);
    }

    @DeleteMapping("delete/{id}")
    void deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
    }
}
