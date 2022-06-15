package com.zulvit.userDatabaseSpring.service;

import com.zulvit.userDatabaseSpring.model.Message;
import com.zulvit.userDatabaseSpring.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final List<Message> messages = new ArrayList<>();
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> list() {
        return (List<Message>) messageRepository.findAll();
    }

    public Optional<String> getMessageId(int id) {
        for (Message message : messages) {
            if (message.getId() == id) {
                return Optional.of(message.getMessage());
            }
        }
        return Optional.empty();
    }

    public Message postMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(int id) {
        for (int i = 0; i < messages.size(); i++) {
            if (this.messages.get(i).getId() == id) {
                messages.remove(id);
            }
        }
    }
}
