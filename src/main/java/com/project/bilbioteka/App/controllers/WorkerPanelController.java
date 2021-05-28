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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
        return "worker_users";
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

    @GetMapping("/worker/users/{userId}/prebooked")
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
        model.addAttribute("user_id",userId);
        return "pre_booked_user";
    }

    @GetMapping("/worker/users/{userId}/booked")
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

        model.addAttribute("BookedBooks", bookedBooks);
        model.addAttribute("user_id",userId);

        return "booked_user";
    }

    @PostMapping("/worker/users/{userId}/booked/{bookId}/returnBook")
    public String returnBook(@PathVariable Long userId, @PathVariable Long bookId)
    {
        Book book = bookRepository.getOne(bookId);
        AppUser appUser = userService.getUser(userId.toString());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = String.valueOf(java.time.LocalDate.now());
        LocalDate date1 = LocalDate.parse(today, dtf);
        //LocalDate date2 = LocalDate.parse(book.getDateOfBorrow(), dtf);
        LocalDate date2 = LocalDate.parse("2021-04-20", dtf);
        long daysBetween = ChronoUnit.DAYS.between(date2, date1);
        System.out.println ("Days: " + daysBetween);
        if( daysBetween > 30 )
        {
            appUser.setPenalty(true);
            Long currentPenalty = appUser.getPenaltySum();
            appUser.setPenaltySum(currentPenalty+daysBetween-30);
        }


        appUser.removeBook(book);
        book.setIsAvailable(true);
        book.setAppUser(null);
        book.setDateOfBorrow(null);
        bookRepository.save(book);
        userRepository.save(appUser);

        return "redirect:/worker/users/{userId}/booked";
    }

    @PostMapping("/worker/users/{userId}/prebooked/{bookId}/confirm")
    public String confirmPreBook(@PathVariable Long userId, @PathVariable Long bookId)
    {
        Book book = bookRepository.getOne(bookId);
        book.setIsAvailable(false);
        book.setIsPreBooked(false);
        book.setDateOfBorrow(String.valueOf(java.time.LocalDate.now()));
        bookRepository.save(book);

        return "redirect:/worker/users/{userId}/prebooked";
    }

    @PostMapping("/worker/users/{userId}/penalty/remove")
    public String removePenalty(@PathVariable Long userId)
    {
        AppUser appUser = userService.getUser(userId.toString());
        appUser.setPenalty(false);
        appUser.setPenaltySum(0L);

        userRepository.save(appUser);
        return "redirect:/worker/users";
    }

    @PostMapping("/worker/users/{userId}/prebooked/{bookId}/deny")
    public String denyPreBook(@PathVariable Long userId, @PathVariable Long bookId)
    {
        Book book = bookRepository.getOne(bookId);
        book.setIsAvailable(true);
        book.setIsPreBooked(false);
        bookRepository.save(book);

        return "redirect:/worker/users/{userId}/prebooked";
    }
}
