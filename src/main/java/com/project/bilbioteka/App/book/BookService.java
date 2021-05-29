package com.project.bilbioteka.App.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book)
    {
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);

        return books;
    }
    public List<Book> getBooksByName(String providedString) {
        List<Book> books = new ArrayList<>();
        String searchString = "%" + providedString + "%";
        bookRepository.findByTitleContaining(providedString).forEach(books::add);
        return books;
    }

    public List<Book> getBooksByAuthor(String providedString) {
        List<Book> books = new ArrayList<>();
        String searchString = "%" + providedString + "%";
        bookRepository.findByAuthorContaining(providedString).forEach(books::add);

        return books;
    }


    public void deleteBookById(Long id) { bookRepository.deleteById(id);}
}
