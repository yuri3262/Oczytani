package com.project.bilbioteka.App.book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;



@Controller
public class NewBookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping("/newBook")
    public String newBookForm(Model model)
    {
        List<BookCategoriesENUM> allCategoriesNames = BookCategoriesENUM.getBookCategoriesArray();
        List<BookCategory> allCategories = new ArrayList<>();
        long id = 0;
        for(BookCategoriesENUM name : allCategoriesNames)
        {
            allCategories.add(new BookCategory(id++,name));
        }

        Integer av = 0;
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("allCategories", allCategories);

        return "add_book";
    }

    @PostMapping("/newBook")
    public String newBookSubmit(@ModelAttribute("book") Book book, Model model)
    {
        model.addAttribute("book",book);
//        System.out.println(book.toString());
        model.addAttribute(book);


        bookRepository.save(book);



        return "add_book_submit";
    }
}
