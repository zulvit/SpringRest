package com.zulvit.userDatabaseSpring.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public String adminPage(){
        return "adminPage";
    }
    @GetMapping("/seller")
    @PreAuthorize("hasAnyAuthority('seller:read', 'seller:write', 'admin:read')")
    public String getSeller(){
        return "SellerPage";
    }

    @GetMapping("/store")
    @PreAuthorize("hasAnyAuthority('store_keeper:read', 'admin:read')")
    public String getStore(){
        return "StorePage";
    }
}
