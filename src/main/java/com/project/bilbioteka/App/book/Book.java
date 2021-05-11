package com.project.bilbioteka.App.book;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String author;
    private String publisher;
    @ElementCollection
    private List<String> category = new ArrayList<>();
    private int numberOfPages;
    private Date dateOfPublication;
    private boolean isAvailable = false;


    public Book(String name, String author, String publisher, List<String> category, int numberOfPages, Date dateOfPublication) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.numberOfPages = numberOfPages;
        this.dateOfPublication = dateOfPublication;
    }
}
