package com.project.bilbioteka.App.book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum BookCategoriesENUM {
    CAT1("ACTION"),
    CAT2("ADVENTURE"),
    CAT3("CRIMINAL");
//    CAT4("EDUCATION"),
//    CAT5("HISTORY"),
//    CAT6("HORROR"),
//    CAT7("POETRY"),
//    CAT8("ROMANCE"),
//    CAT9("SCI-FI"),
//    CAT10("THRILLER");



    private static final List<String> bookCategoriesArray;
    public final String label;

    private BookCategoriesENUM(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    static {
        bookCategoriesArray = new ArrayList<>();
        for(BookCategoriesENUM x : BookCategoriesENUM.values())
        {
            bookCategoriesArray.add(x.label);
        }
    }

    public static List<String> getBookCategoriesArray() {
        return Collections.unmodifiableList(bookCategoriesArray);
    }
}
