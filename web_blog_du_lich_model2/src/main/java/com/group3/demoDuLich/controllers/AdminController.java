package com.group3.demoDuLich.controllers;

import com.group3.demoDuLich.models.Blog;
import com.group3.demoDuLich.models.Image;
import com.group3.demoDuLich.models.Role;
import com.group3.demoDuLich.models.User;
import com.group3.demoDuLich.repository.IAccountRepo;
import com.group3.demoDuLich.repository.IBlogRepo;
import com.group3.demoDuLich.repository.IImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAccountRepo iAccountRepo;
    @Autowired
    IBlogRepo iBlogRepo;
    @Autowired
    IImageRepo iImageRepo;

    @GetMapping
    public String show(Model model) {
        List<User> users = (List<User>) iAccountRepo.findAll();
        List<Blog> blogs = (List<Blog>) iBlogRepo.findAll();
        model.addAttribute("users", users);
        model.addAttribute("blogs", blogs);
        model.addAttribute("user", new User());
        model.addAttribute("blog", new Blog());
        return "/admin/admin";
    }

    @PostMapping("/user")
    public String register(@ModelAttribute User user) {
        if (user.getRole() == null) {
            Role role = new Role();
            role.setId(2);
            user.setRole(role);
        }
        if (user.getImg() == null) {
            user.setImg("/image/client.png");
        }
        iAccountRepo.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/user/{id}")
    public String showEdit(Model model, @PathVariable int id) {
        User user = iAccountRepo.findById(id).get();
        List<User> users = (List<User>) iAccountRepo.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        return "/admin/editUser";
    }

    @GetMapping("/blog/{id}")
    public String showEditBlog(Model model, @PathVariable int id) {
        Blog blog = iBlogRepo.findById(id).get();
        List<User> users = (List<User>) iAccountRepo.findAll();
        model.addAttribute("blog", blog);
        return "/admin/editBlog";
    }

    @GetMapping("/blog/delete/{id}")
    public String delete(@PathVariable int id) {
        iBlogRepo.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/blog")
    public String showCreateBlog(Model model) {
        model.addAttribute("blog", new Blog());
        return "/admin/createBlog";
    }

    @PostMapping("/blog")
    public String createBlog(@ModelAttribute Blog blog, @RequestParam(defaultValue = "https://luhanhvietnam.com.vn/du-lich/vnt_upload/news/10_2020/dia-diem-chup-anh-dep-o-phu-quoc-ba.jpg") String img) {
        Image image = new Image();
        image.setUrl(img);
        image = iImageRepo.save(image);
        Set<Image> images = new HashSet<>();
        images.add(image);
        blog.setImages(images);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = iAccountRepo.findAccountByUsername(userDetails.getUsername());
        blog.setUser(user);
        iBlogRepo.save(blog);
        return "redirect:/admin";
    }
}
