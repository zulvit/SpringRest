package com.zulvit.userDatabaseSpring.service;

import com.zulvit.userDatabaseSpring.database.NoteRepository;
import com.zulvit.userDatabaseSpring.exception.NoteNotFoundException;
import com.zulvit.userDatabaseSpring.exception.NoteVoidException;
import com.zulvit.userDatabaseSpring.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    //database
    @Autowired
    private NoteRepository noteRepository;

    public List<Note> list() throws NoteVoidException {
        List<Note> list = new ArrayList<>();
        Iterable<Note> allMessages = this.noteRepository.findAll();
        allMessages.forEach(list::add);
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new NoteVoidException("There are no messages in the database yet");
        }
    }

    public Optional<Note> getMessageId(long id) throws NoteNotFoundException {
        Optional<Note> messageObj = this.noteRepository.findById(id);
        if (messageObj.isPresent()) {
            this.noteRepository.findAllById(Collections.singleton(id));
            return messageObj;
        } else {
            throw new NoteNotFoundException("There is no message with this ID.");
        }
    }

    public void postMessage(Note note) {
        this.noteRepository.save(note);
    }

    public void deleteMessage(long id) {
        this.noteRepository.deleteById(id);
    }
}
