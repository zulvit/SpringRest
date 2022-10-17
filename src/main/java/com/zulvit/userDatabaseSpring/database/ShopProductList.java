package com.zulvit.userDatabaseSpring.database;

import com.zulvit.userDatabaseSpring.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ShopProductList extends JpaRepository<Shop, Long> {
}
