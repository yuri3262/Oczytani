package com.project.bilbioteka.App.book;

import javax.persistence.*;

@Embeddable
public class BookCategory {

    private Long id;
    private BookCategoriesENUM name;

    public BookCategory(Long id,BookCategoriesENUM name) {
        this.id = id;
        this.name = name;
    }

    public BookCategory() {
    }

    public Long getId() {
        return id;
    }

    public BookCategoriesENUM getName() {
        return name;
    }


}
