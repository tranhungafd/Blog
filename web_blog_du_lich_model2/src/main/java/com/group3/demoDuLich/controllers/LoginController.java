package com.group3.demoDuLich.controllers;

import com.group3.demoDuLich.models.Blog;
import com.group3.demoDuLich.models.Role;
import com.group3.demoDuLich.models.User;
import com.group3.demoDuLich.repository.IAccountRepo;
import com.group3.demoDuLich.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping
    public String showLoginForm() {
        return "login";
    }
}
