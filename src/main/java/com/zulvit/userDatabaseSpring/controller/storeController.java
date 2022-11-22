package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.model.Invoice;
import com.zulvit.userDatabaseSpring.model.Order;
import com.zulvit.userDatabaseSpring.service.InvoiceService;
import com.zulvit.userDatabaseSpring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/store")
public class storeController {
    private final OrderService orderService;
    private final InvoiceService invoiceService;

    @Autowired
    public storeController(OrderService orderService, InvoiceService invoiceService) {
        this.orderService = orderService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/orders")
    public String getOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String getMoreInfo(Model model, @PathVariable("id") Long id) {
        List<Invoice> invoices = invoiceService.getInvoicesById(id);
        model.addAttribute("invoices", invoices);
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "moreInvoicesInfo";
    }

    @GetMapping("/orders/complete/{id}")
    public String completeOrder(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        invoiceService.deleteByOrderId(id);
        return "redirect:/store/orders/";
    }
}
