package com.project.bilbioteka.App.controllers;

import com.project.bilbioteka.App.book.Book;
import com.project.bilbioteka.App.book.BookRepository;
import com.project.bilbioteka.App.book.BookService;
import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserRepository;
import com.project.bilbioteka.App.user.AppUserService;
import com.project.bilbioteka.App.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class WorkerPanelController {

    @Autowired
    private AppUserService userService;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/worker/users")
    public String users(Model model) {
        List<AppUser> users = userService.getAllUsers();

        // do not display admin and worker users
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getRole() == UserRole.ADMIN || users.get(i).getRole() == UserRole.WORKER)
                users.remove(i);
        }

        model.addAttribute("users", users);
        return "manage_users_panel";
    }

    @GetMapping("/books")
    public String books(Model model) {
        List<Book> books = bookService.getAllBooks();

        model.addAttribute("books", books);
        return "manage_books_panel";
    }

    @PostMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id)
    {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/worker/users/{userId}/prebooked") //przetestowane, zbiera tylko prebookniete ksiazki //TODO: frontend lista wszystkich prebooknietych ksiazek
    public String listPreBookedBooksForUser(@PathVariable Long userId, Model model)
    {
        AppUser appUser = userService.getUser(userId.toString());
        List <Book> preBookedBooks = new ArrayList<>();
        Set <Book> s = appUser.getBooks();

        for( Book b : s) {
            if (b.getIsPreBooked()) {
                preBookedBooks.add(b);
            }
        }

        model.addAttribute("preBookedBooks", preBookedBooks);

        return "pre_booked_user";

    }

    @GetMapping("/worker/users/{userId}/booked") //powinno dzialac //TODO: frontend - lista wszystkich wypozyczonych ksiazek uzytkownika
    public String listBookedBooksForUser(@PathVariable Long userId, Model model)
    {
        AppUser appUser = userService.getUser(userId.toString());
        List <Book> bookedBooks = new ArrayList<>();
        Set <Book> s = appUser.getBooks();

        for( Book b : s) {
            if (!b.getIsAvailable()) { // zakladamy ze jezeli isAvailable = false to ksiazka jest wypozyczona?
                bookedBooks.add(b);
            }
        }

        model.addAttribute("preBookedBooks", bookedBooks);

        return "booked_user";

    }

    @PostMapping("/worker/users/{userId}/booked/{bookId}/returnBook") //nie testowane //TODO: frontend - guzik do oddawania ksiazki
    public String returnBook(@PathVariable Long userId, @PathVariable Long bookId)
    {
        Book book = bookRepository.getOne(bookId);
        AppUser appUser = userService.getUser(userId.toString());

        appUser.removeBook(book);
        book.setIsAvailable(true);
        book.setAppUser(null);
        bookRepository.save(book);
        userRepository.save(appUser);

        return "redirect:/worker/users/{userId}/booked";

    }

    @PostMapping("/worker/users/{userId}/prebooked/{bookId}/confirm") //powinno dzialac //TODO: frontend - przycisk confirm w liscie prebooknietych ksiazek
    public String confirmPreBook(@PathVariable Long userId, @PathVariable Long bookId)
    {
        Book book = bookRepository.getOne(bookId);
        book.setIsAvailable(false);
        book.setIsPreBooked(false);
        bookRepository.save(book);

        return "redirect:/worker/users/{userId}/prebooked";
    }

    @PostMapping("/worker/users/{userId}/prebooked/{bookId}/deny") //powinno dzialac //TODO: frontend przycisk deny w liscie prebooknietych ksiazek
    public String denyPreBook(@PathVariable Long userId, @PathVariable Long bookId)
    {
        Book book = bookRepository.getOne(bookId);
        book.setIsAvailable(true);
        book.setIsPreBooked(false);
        bookRepository.save(book);

        return "redirect:/worker/users/{userId}/prebooked";
    }
}
