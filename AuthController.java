package com.example.library.controller;

import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.library.model.User;
import com.example.library.repository.UserRepository;

@Controller
public class AuthController {
    private final UserRepository userRepo;
    public AuthController(UserRepository userRepo){ this.userRepo = userRepo; }

    @GetMapping({"/","/login"})
    public String loginPage(){ return "login"; }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session, Model model){
        Optional<User> u = userRepo.findByUsername(username);
        if (u.isPresent() && u.get().getPassword().equals(password)) {
            session.setAttribute("user", u.get());
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("error","Invalid credentials");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
