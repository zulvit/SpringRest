package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.model.User;
import com.zulvit.userDatabaseSpring.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/list")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = adminService.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/users/delete/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String deleteUser(@PathVariable("id") Long id){
        adminService.deleteById(id);
        return "redirect:/users";
    }
}
