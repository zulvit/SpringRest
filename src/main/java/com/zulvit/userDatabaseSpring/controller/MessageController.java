package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.model.Message;
import com.zulvit.userDatabaseSpring.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping()
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService message) {
        this.messageService = message;
    }

    @GetMapping("/messages")
    public List<Message> list() {
        return messageService.list();
    }

    @GetMapping("messages/{id}")
    public Optional<String> getFirst(@PathVariable int id) {
        return messageService.getMessageId(id);
    }

    @PostMapping("message/add")
    Message postMessage(@RequestBody Message message) {
        return messageService.postMessage(message);
    }

    @DeleteMapping("message/delete/{id}")
    void deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
    }
}
