package com.zulvit.userDatabaseSpring.service;

import com.zulvit.userDatabaseSpring.database.MessageRepository;
import com.zulvit.userDatabaseSpring.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final List<Message> messages = new ArrayList<>();

    //database
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> list() {
        this.messageRepository.findAll();
        return messages;
    }

    public Optional<String> getMessageId(long id) {
        this.messageRepository.findAllById(Collections.singleton(id));
        for (Message message : messages) {
            if (message.getId() == id) {
                return Optional.of(message.getMessage());
            }
        }
        return Optional.empty();
    }

    public void postMessage(Message message) {
        this.messageRepository.save(message);
        this.messages.add(message);
    }

    public void deleteMessage(long id) {
        this.messageRepository.deleteById(id);
        for (int i = 0; i < messages.size(); i++) {
            if (this.messages.get(i).getId() == id) {
                messages.remove(id - 1);
            }
        }
    }
}
