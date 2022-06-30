package com.zulvit.userDatabaseSpring.service;

import com.zulvit.userDatabaseSpring.database.MessageRepository;
import com.zulvit.userDatabaseSpring.exception.MessageNotFoundException;
import com.zulvit.userDatabaseSpring.exception.MessagesVoidException;
import com.zulvit.userDatabaseSpring.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    //database
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> list() throws MessagesVoidException {
        List<Message> list = new ArrayList<>();
        Iterable<Message> allMessages = this.messageRepository.findAll();
        allMessages.forEach(list::add);
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new MessagesVoidException("There are no messages in the database yet");
        }
    }

    public Optional<Message> getMessageId(long id) throws MessageNotFoundException {
        Optional<Message> messageObj = this.messageRepository.findById(id);
        if (messageObj.isPresent()) {
            this.messageRepository.findAllById(Collections.singleton(id));
            return messageObj;
        } else {
            throw new MessageNotFoundException("There is no message with this ID.");
        }
    }

    public void postMessage(Message message) {
        this.messageRepository.save(message);
    }

    public void deleteMessage(long id) {
        this.messageRepository.deleteById(id);
    }
}
