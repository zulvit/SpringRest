package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.model.Invoice;
import com.zulvit.userDatabaseSpring.model.Order;
import com.zulvit.userDatabaseSpring.service.InvoiceService;
import com.zulvit.userDatabaseSpring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//TODO Сделать html для списка заказов (orders), сделать форму для создания заказа
@Controller
@RequestMapping("/seller")
public class SellerController {
    private final OrderService orderService;
    private final InvoiceService invoiceService;

    @Autowired
    public SellerController(OrderService orderService, InvoiceService invoiceService) {
        this.orderService = orderService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/create-order")
    public String createOrder(Model model) {
        model.addAttribute("order", new Order());
        return "CreateOrder";
    }

    @PostMapping(path = "/create-order", consumes = "application/x-www-form-urlencoded")
    public String createNewOrder(Order order) {
        orderService.saveOrder(order);
        return "redirect:/seller/orders";
    }

    @GetMapping("/orders")
    public String ordersList(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String moreOrderInfo(@PathVariable("id") Long id, Model model) {
        List<Invoice> invoices = invoiceService.getInvoicesById(id);
        model.addAttribute("invoices", invoices);
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "moreInvoicesInfo";
    }

    @GetMapping("/orders/new-invoice/{id}")
    public String newInvoice(Model model, @PathVariable("id") Long id) {
//        Invoice invoice = new Invoice();
//        invoice.setOrder_id(id);
        model.addAttribute("invoice", new Invoice());
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "newInvoice";
    }

    @PostMapping(path = "/orders/new-invoice/{id}", consumes = "application/x-www-form-urlencoded")
    public String saveNewInvoice(Invoice invoice, @PathVariable("id") Long id) {
        invoice.setOrder_id(id);
        invoiceService.saveInvoice(invoice);
        return "redirect:/seller/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        return "redirect:/seller/orders";
    }
}
