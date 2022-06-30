package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.exception.NoteNotFoundException;
import com.zulvit.userDatabaseSpring.exception.NoteVoidException;
import com.zulvit.userDatabaseSpring.model.Note;
import com.zulvit.userDatabaseSpring.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService message) {
        this.noteService = message;
    }

    @GetMapping("")
    public ResponseEntity<String> list() {
        try {
            List<Note> list = noteService.list();
            return ResponseEntity.ok("Список заметок: \n" + list);
        } catch (NoteVoidException e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.badRequest().body("Ещё нет ни одной заметки.");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.badRequest().body("Произошла непредвиденная ошибка.");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getFirst(@PathVariable int id) {
        try {
            Optional<Note> messageId = this.noteService.getMessageId(id);
            return ResponseEntity.ok("Заметка найдена: \n" + "\"" + messageId.get().getNote() + "\"");
        } catch (NoteNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.badRequest().body("Заметка не найдена.");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.badRequest().body("Произошла непредвиденная ошибка.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> postMessage(@RequestBody Note note) {
        try {
            noteService.postMessage(note);
            return ResponseEntity.ok("Заметка добавлена.");
        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
            return ResponseEntity.badRequest().body("Произошла ошибка при добавлении.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable int id) {
        try {
            noteService.deleteMessage(id);
            return ResponseEntity.ok("Заметка удалена.");
        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
            return ResponseEntity.badRequest().body("Произошла ошибка при удалении.");
        }
    }
}
