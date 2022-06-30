package com.zulvit.userDatabaseSpring.database;

import com.zulvit.userDatabaseSpring.model.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {
}
