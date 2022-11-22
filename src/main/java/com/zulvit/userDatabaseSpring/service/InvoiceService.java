package com.zulvit.userDatabaseSpring.service;

import com.zulvit.userDatabaseSpring.database.InvoiceRepository;
import com.zulvit.userDatabaseSpring.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice findById(Long id) {
        return invoiceRepository.getOne(id);
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }

    public void update(Long id, Invoice invoice) {
        Invoice invoiceToBeUpdated = findById(id);
        saveInvoice(invoice);
    }

    public List<Invoice> getInvoicesById(Long id) {
        List<Invoice> invoices = new ArrayList<>();
        for (int i = 0; i < invoiceRepository.findAll().size(); i++) {
            if(invoiceRepository.findAll().get(i).getOrder_id()==id){
                invoices.add(invoiceRepository.findAll().get(i));
            }
        }
        return invoices;
    }

    public void deleteByOrderId(Long id){
        List<Invoice> invoices = invoiceRepository.findAll();
        List<Long> invoicesId = new ArrayList<>();
        for (int i = 0; i < invoices.size(); i++) {
            if(invoices.get(i).getOrder_id()==id){
                invoicesId.add(invoices.get(i).getId());
            }
        }
        invoicesId.forEach(invoiceRepository::deleteById);
    }
}
