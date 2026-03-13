package com.example.ngogiaphuong2280602523.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ngogiaphuong2280602523.model.User;
import com.example.ngogiaphuong2280602523.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/login";
    }

    @PostMapping("/login")
    public String processLogin(String username, String password, HttpSession session, Model model) {
        User user = userService.loginUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/"; // redirect home
        }
        model.addAttribute("error", "Username hoặc mật khẩu chưa chính xác!");
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "auth/register";
        }
        
        if (userService.isUsernameTaken(user.getUsername())) {
            model.addAttribute("error", "Tài khoản đã tồn tại!");
            return "auth/register";
        }
        
        if (userService.isEmailTaken(user.getEmail())) {
            model.addAttribute("error", "Email đã có người sử dụng!");
            return "auth/register";
        }
        
        userService.registerUser(user);
        model.addAttribute("message", "Đăng ký thành công! Hãy đăng nhập.");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/";
    }
}
