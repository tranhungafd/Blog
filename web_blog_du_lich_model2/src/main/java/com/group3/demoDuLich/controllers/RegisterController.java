package com.group3.demoDuLich.controllers;

import com.group3.demoDuLich.models.Role;
import com.group3.demoDuLich.models.User;
import com.group3.demoDuLich.repository.IAccountRepo;
import com.group3.demoDuLich.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    IAccountRepo iAccountRepo;

    @GetMapping
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute User user) {
        if (user.getRole() == null) {
            Role role = new Role();
            role.setId(2);
            user.setRole(role);
        }
        if (user.getImg() == null) {
            user.setImg("image/client.png");
        }
        iAccountRepo.save(user);
        return "redirect:/login";
    }

}
