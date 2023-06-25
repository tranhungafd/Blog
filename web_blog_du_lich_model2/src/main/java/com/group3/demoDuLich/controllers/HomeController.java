package com.group3.demoDuLich.controllers;

import com.group3.demoDuLich.models.Blog;
import com.group3.demoDuLich.models.Comment;
import com.group3.demoDuLich.models.User;
import com.group3.demoDuLich.repository.IAccountRepo;
import com.group3.demoDuLich.repository.IBlogRepo;
import com.group3.demoDuLich.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    IBlogRepo iBlogRepo;
    @Autowired
    IAccountRepo iAccountRepo;

    @Autowired
    ICommentRepo iCommentRepo;

    @GetMapping
    public String showHomePage(Model model) {
        List<Blog> blogs1_3 = iBlogRepo.findTopByComment(1, 0, 3);
        List<Blog> blogs3_6 = iBlogRepo.findTopByComment(1, 3, 3);
        model.addAttribute("blogs1_3", blogs1_3);
        model.addAttribute("blogs3_6", blogs3_6);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authentication", authentication);

        return "index";
    }

    @GetMapping("blog/{id}")
    public String showDaLat(@PathVariable int id, Model model) {
        List<Blog> blogs1_5 = iBlogRepo.findTopByComment(1, 0, 5);
        Blog blog = iBlogRepo.findById(id).get();
        blog.setView(blog.getView() + 1);
        iBlogRepo.save(blog);
        model.addAttribute("blog", blog);
        model.addAttribute("blogs1_5", blogs1_5);
        return "showBlog";
    }

    @PostMapping("comment/{id}")
    public String comment(Comment comment, @RequestParam(defaultValue = "0") int rating, @PathVariable int id) {
        comment.setCount(rating);
        //Lấy ra user đang đăng nhập.
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = iAccountRepo.findAccountByUsername(userDetails.getUsername());
        comment.setUser(user);
        comment = iCommentRepo.save(comment);

        Blog blog = iBlogRepo.findById(id).get();
        Set<Comment> comments = blog.getComments();
        comments.add(comment);
        blog.setComments(comments);
        iBlogRepo.save(blog);
        return "redirect:/blog/" + id;
    }

    @GetMapping("blogs/{id}")
    public String showFoodtourForm(@PathVariable int id, Model model) {
        List<Blog> blogs = iBlogRepo.findTopByComment(id, 0, 6);
        model.addAttribute("blogs", blogs);
        return "blogs";
    }

    @GetMapping("search")
    public String search(@RequestParam(defaultValue = "") String title, Model model) {
        List<Blog> blogs = iBlogRepo.search(title);
        model.addAttribute("blogs", blogs);
        return "blogs";
    }


    @GetMapping("vechungtoi")
    public String showVeChungToi() {
        return "vechungtoi";
    }

    @GetMapping("success")
    public String showSuccessForm() {
        return "success";
    }

    @GetMapping("hoian")
    public String showHoiAn() {
        return "hoian";
    }

    @GetMapping("hochiminh")
    public String showHoChiMinh() {
        return "hochiminh";
    }

    @GetMapping("guidebook")
    public String showGuideForm() {
        return "guidebook";
    }


    @GetMapping("foodreview")
    public String showFoodReviewForm() {
        return "foodreview1";
    }

    @GetMapping("danang")
    public String showDaNang() {
        return "danang";
    }

    @GetMapping("dalat")
    public String showDaLat() {
        return "dalat";
    }
    @GetMapping("dichvu")
    public String showDichVuForm() {
        return "dichvu";
    }

}
