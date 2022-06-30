package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.exception.MessageNotFoundException;
import com.zulvit.userDatabaseSpring.exception.MessagesVoidException;
import com.zulvit.userDatabaseSpring.model.Message;
import com.zulvit.userDatabaseSpring.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    public ResponseEntity<String> list() {
        try {
            List<Message> list = messageService.list();
            return ResponseEntity.ok("Список сообщений: \n" + list);
        } catch (MessagesVoidException e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.badRequest().body("Ещё нет ни одного сообщения.");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.badRequest().body("Произошла непредвиденная ошибка.");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getFirst(@PathVariable int id) {
        try {
            Optional<Message> messageId = this.messageService.getMessageId(id);
            return ResponseEntity.ok("Сообщение найдено: \n" + "\"" + messageId.get().getMessage() + "\"");
        } catch (MessageNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.badRequest().body("Произошла ошибкка, сообщение не найдено.");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.badRequest().body("Произошла непредвиденная ошибка.");
        }
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
