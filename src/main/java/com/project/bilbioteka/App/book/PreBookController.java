package com.project.bilbioteka.App.book;


import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserRepository;
import com.project.bilbioteka.App.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;



@Controller
public class PreBookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BookService bookService;

    @GetMapping("/prebook")
    public String newBookForm(Model model)
    {
        List<Book> books = bookService.getAllBooks();

        model.addAttribute("books", books);
        return "pre_book";
    }


    @PostMapping("/prebook/{id}") //TODO: frontend
    @ResponseBody
    public String preBook(@PathVariable Long id, Principal principal)
    {
        Book book = bookRepository.getOne(id);
        AppUser user = appUserService.findAppUserByName(principal.getName());

        user.addPreBook(book);
        appUserRepository.save(user);

        book.setAppUser(user);
        book.setIsPreBooked(true);
        bookRepository.save(book);

        AppUser user2 = appUserService.findAppUserByName(principal.getName()); //test czy sie dobrze w bazie zapisalo

        Set<Book> testBooks = user2.getBooks();

        StringBuilder string = new StringBuilder();
        for(Book b : testBooks)
        {
            string.append(b.getTitle()).append(" | ");
        }

        return string.toString();
    }


}
