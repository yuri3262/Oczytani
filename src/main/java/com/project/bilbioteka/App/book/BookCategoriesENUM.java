package com.project.bilbioteka.App.book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

enum BookCategoriesENUM {
    ACTION,
    ADVENTURE,
    CRIMINAL,
    EDUCATION,
    HISTORY,
    HORROR,
    POETRY,
    ROMANCE,
    SCI_FI,
    THRILLER;


    private static final List<BookCategoriesENUM> bookCategoriesArray;

    static {
        bookCategoriesArray = new ArrayList<>();
        bookCategoriesArray.addAll(Arrays.asList(BookCategoriesENUM.values()));
    }

    public static List<BookCategoriesENUM> getBookCategoriesArray() {
        return Collections.unmodifiableList(bookCategoriesArray);
    }

}
