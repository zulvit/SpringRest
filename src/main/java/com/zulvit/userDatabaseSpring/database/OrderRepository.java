package com.zulvit.userDatabaseSpring.database;
import com.zulvit.userDatabaseSpring.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
