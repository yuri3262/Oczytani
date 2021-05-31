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
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookServiceTests {

    @Autowired
    private BookService bookService;

    @Test
    public void addBooksAndGetAllOfThem_1() {
        int initialSize = bookService.getAllBooks().size();

        Book book = new Book();
        bookService.addBook(book);

        assertEquals(initialSize + 1, bookService.getAllBooks().size());

        bookService.deleteBookById(book.getId());
    }

    @Test
    public void addBooksAndGetAllOfThem_2() {
        int initialSize = bookService.getAllBooks().size();

        List<Book> books = new ArrayList<Book>();
        for(int i=0;i<4;i++) {
            books.add(new Book());
        }

        for(Book book : books) {
            bookService.addBook(book);
        }

        assertEquals(initialSize + 4, bookService.getAllBooks().size());

        for(Book book : books) {
            bookService.deleteBookById(book.getId());
        }
    }

}
