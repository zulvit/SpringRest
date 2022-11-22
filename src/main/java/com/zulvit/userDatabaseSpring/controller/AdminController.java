package com.zulvit.userDatabaseSpring.controller;

import com.zulvit.userDatabaseSpring.model.Product;
import com.zulvit.userDatabaseSpring.model.Role;
import com.zulvit.userDatabaseSpring.model.User;
import com.zulvit.userDatabaseSpring.service.AdminService;
import com.zulvit.userDatabaseSpring.service.PasswordEncoderService;
import com.zulvit.userDatabaseSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(value = "/list")
public class AdminController {
    private final AdminService adminService;
    private final PasswordEncoderService passwordEncoderService;
    private final ProductService productService;

    @Autowired
    public AdminController(AdminService adminService, PasswordEncoderService passwordEncoderService, ProductService productService) {
        this.adminService = adminService;
        this.passwordEncoderService = passwordEncoderService;
        this.productService = productService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = adminService.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        adminService.deleteById(id);
        return "redirect:/list/users";
    }

    @PostMapping(path = "/users/create", consumes = "application/x-www-form-urlencoded")
    public String createUser(User user) {
        String password = user.getPassword();
        password = passwordEncoderService.encode(password);
        System.out.println(password);
        user.setPassword(password);
        adminService.saveUser(user);
        return "redirect:/list/users";
    }

    @GetMapping("/users/create")
    public String getSavePage(Model model) {
        model.addAttribute("user", new User());
        List<String> rolelist = Arrays.asList("ADMIN", "SELLER", "STOREKEEPER");
        model.addAttribute("roleList", rolelist);
        return "userCreate";
    }

    @GetMapping("/users/update/{id}")
    public String updateUser(Model model, @PathVariable(name = "id") Long id) {
        User user = adminService.findById(id);
        model.addAttribute("user", adminService.findById(id));
        List<Enum> rolelist = new ArrayList<>();
        rolelist.add(Role.ADMIN);
        rolelist.add(Role.SELLER);
        rolelist.add(Role.STORE_KEEPER);
        model.addAttribute("roleList", rolelist);
        return "editUser";
    }

    @PostMapping("/users/update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        String password = user.getPassword();
        password = passwordEncoderService.encode(password);
        user.setPassword(password);
        adminService.update(id, user);
        return "redirect:/list/users";
    }


    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "productList";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/list/products";
    }

    @PostMapping(path = "/products/create", consumes = "application/x-www-form-urlencoded")
    public String createProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/list/products";
    }

    @GetMapping("/products/create")
    public String productSavePage(Model model) {
        model.addAttribute("product", new Product());
        return "productCreate";
    }

    @GetMapping("/products/update/{id}")
    public String updateProduct(Model model, @PathVariable(name = "id") Long id) {
        Product product = productService.findById(id);
        model.addAttribute("product", productService.findById(id));
        return "editProduct";
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(@ModelAttribute("product") Product product, @PathVariable("id") Long id) {
        productService.update(id, product);
        return "redirect:/list/products";
    }
}
