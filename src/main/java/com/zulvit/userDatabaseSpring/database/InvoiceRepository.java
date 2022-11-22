package com.zulvit.userDatabaseSpring.database;

import com.zulvit.userDatabaseSpring.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
