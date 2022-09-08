package com.zulvit.userDatabaseSpring.database;

import com.zulvit.userDatabaseSpring.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
