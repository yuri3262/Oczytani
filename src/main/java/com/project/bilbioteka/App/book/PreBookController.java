package com.project.bilbioteka.App.book;


import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserRepository;
import com.project.bilbioteka.App.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String newBookForm(HttpServletRequest request, Model model)
    {
        String searchString = "";
        if(request.getParameter("search") != null)
            searchString = request.getParameter("search");

        List<Book> books = null;
        if(searchString.isEmpty())
            books = bookService.getAllBooks();
        else 
            books = bookService.getBooksByName(searchString);

        model.addAttribute("books", books);
        return "pre_book";
    }


    @PostMapping("/prebook/{id}") //TODO: frontend
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

        return "pre_book_success";
    }

    @GetMapping("/mybooks")
    public String myBooks(Model model,Principal principal){
        AppUser user = appUserService.findAppUserByName(principal.getName());

        Set<Book> myBooks = user.getBooks();
        Long myPenalty = user.getPenaltySum();
        model.addAttribute("mybooks",myBooks);
        model.addAttribute("myPenalty",myPenalty);

        return "my_books";
    }


}
