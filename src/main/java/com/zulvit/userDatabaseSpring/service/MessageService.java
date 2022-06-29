package com.zulvit.userDatabaseSpring.service;

import com.zulvit.userDatabaseSpring.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final List<Message> messages = new ArrayList<>();

    public List<Message> list() {
        return messages;
    }

    public Optional<String> getMessageId(int id) {
        for (Message message : messages) {
            if (message.getId() == id) {
                return Optional.of(message.getMessage());
            }
        }
        return Optional.empty();
    }

    public void postMessage(Message message) {
        this.messages.add(message);
    }

    public void deleteMessage(int id) {
        for (int i = 0; i < messages.size(); i++) {
            if (this.messages.get(i).getId() == id) {
                messages.remove(id - 1);
            }
        }
    }
}
