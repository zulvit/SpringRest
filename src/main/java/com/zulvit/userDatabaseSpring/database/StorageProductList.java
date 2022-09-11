package com.zulvit.userDatabaseSpring.database;

import com.zulvit.userDatabaseSpring.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageProductList extends JpaRepository<Storage, Long> {
}
