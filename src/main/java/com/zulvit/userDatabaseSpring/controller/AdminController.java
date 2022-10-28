package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.model.User;
import com.zulvit.userDatabaseSpring.service.AdminService;
import com.zulvit.userDatabaseSpring.service.PasswordEncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/list")
public class AdminController {
    private final AdminService adminService;
    private final PasswordEncoderService passwordEncoderService;

    @Autowired
    public AdminController(AdminService adminService, PasswordEncoderService passwordEncoderService) {
        this.adminService = adminService;
        this.passwordEncoderService = passwordEncoderService;
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = adminService.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        adminService.deleteById(id);
        return "redirect:/list/users";
    }

    @PostMapping(path = "/users/create", consumes = "application/x-www-form-urlencoded")
    public String createUser(User user){
        String password = user.getPassword();
        password=passwordEncoderService.encode(password);
        System.out.println(password);
        user.setPassword(password);
        adminService.saveUser(user);
        return "redirect:/list/users";
    }

    @GetMapping("/users/create")
    public String getSavePage(Model model){
        model.addAttribute("user", new User());
        return "userCreate";
    }

    @GetMapping("/users/update/{id}")
    public String updateUser(Model model, @PathVariable(name="id") Long id){
        User user = adminService.findById(id);
        model.addAttribute("user", adminService.findById(id));
        return "editUser";
    }

    @PostMapping("/users/update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id){
        String password = user.getPassword();
        System.out.println(password);
        password=passwordEncoderService.encode(password);
        System.out.println(user.getPassword());
        user.setPassword(password);
        System.out.println(user.getPassword());
        System.out.println(user);
        System.out.println(password);
        adminService.update(id, user);
        return "redirect:/list/users";
    }
}
