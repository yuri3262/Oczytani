package com.project.bilbioteka.App.unit;

import com.project.bilbioteka.App.book.Book;
import com.project.bilbioteka.App.book.BookRepository;
import com.project.bilbioteka.App.book.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.jupiter.api.Assertions.*;
import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookServiceTests {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void addBooksAndGetAllOfThem_1() {
        assertEquals(0, bookService.getAllBooks().size());
    }

    @Test
    public void addBooksAndGetAllOfThem_2() {
        bookRepository.deleteAll();

        assertEquals(0, bookService.getAllBooks().size());

        bookService.addBook(new Book());

        assertEquals(1, bookService.getAllBooks().size());
    }

    @Test
    public void addBooksAndGetAllOfThem_3() {
        bookRepository.deleteAll();

        assertEquals(0, bookService.getAllBooks().size());

        bookService.addBook(new Book());
        bookService.addBook(new Book());
        bookService.addBook(new Book());
        bookService.addBook(new Book());

        assertEquals(4, bookService.getAllBooks().size());
    }

}
