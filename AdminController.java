package com.example.library.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final BookRepository bookRepo;
    public AdminController(BookRepository bookRepo){ this.bookRepo = bookRepo; }

    private boolean isAdmin(HttpSession session){
        Object u = session.getAttribute("user");
        return u != null;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session){
        if(!isAdmin(session)) return "redirect:/login";
        model.addAttribute("books", bookRepo.findAll());
        return "dashboard";
    }

    @GetMapping("/book/new")
    public String newBookForm(Model model, HttpSession session){
        if(!isAdmin(session)) return "redirect:/login";
        model.addAttribute("book", new Book());
        return "book_form";
    }

    @PostMapping("/book/save")
    public String saveBook(Book book, HttpSession session){
        if(!isAdmin(session)) return "redirect:/login";
        bookRepo.save(book);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id, HttpSession session){
        if(!isAdmin(session)) return "redirect:/login";
        bookRepo.deleteById(id);
        return "redirect:/admin/dashboard";
    }
}
