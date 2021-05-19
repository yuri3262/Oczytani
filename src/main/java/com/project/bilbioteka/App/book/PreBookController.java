package com.project.bilbioteka.App.book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@Controller
public class PreBookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping("/prebook")
    public String newBookForm(Model model)
    {
        List<Book> books = bookService.getAllBooks();

        model.addAttribute("books", books);
        return "pre_book";
    }


    @PostMapping("/prebook/{id}")
    @ResponseBody
    public String preBook(@PathVariable Long id)
    {
        String ret = "wypozyczasz " + id;
        return  ret;
    }
}
