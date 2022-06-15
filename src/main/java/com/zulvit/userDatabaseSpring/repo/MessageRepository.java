package com.zulvit.userDatabaseSpring.repo;

import com.zulvit.userDatabaseSpring.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
