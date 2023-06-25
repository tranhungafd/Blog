package com.group3.demoDuLich.controllers;

import com.group3.demoDuLich.models.Role;
import com.group3.demoDuLich.models.User;
import com.group3.demoDuLich.repository.IAccountRepo;
import com.group3.demoDuLich.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IBlogRepo iBlogRepo;

    @Autowired
    IAccountRepo iAccountRepo;

    @GetMapping("/{username}")
    public String showProfile(@PathVariable String username, Model model){
        User user = iAccountRepo.findAccountByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping()
    public String register(@ModelAttribute User userNew) {
        User user = iAccountRepo.findAccountByUsername(userNew.getUsername());
        if (userNew.getImg() == null || userNew.getImg().equals("")) {
            userNew.setImg("/image/client.png");
        }
        userNew.setRole(user.getRole());
        userNew.setPassword(user.getPassword());
        iAccountRepo.save(userNew);
        return "redirect:/";
    }

}
