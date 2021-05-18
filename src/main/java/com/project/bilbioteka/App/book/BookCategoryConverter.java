package com.project.bilbioteka.App.book;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookCategoryConverter implements Converter<String, BookCategory> {

    @Override
    public BookCategory convert(String id) {

        List<BookCategoriesENUM> allCategoriesNames = BookCategoriesENUM.getBookCategoriesArray();
        List<BookCategory> allCategories = new ArrayList<>();
        long ID = 0;
        for(BookCategoriesENUM name : allCategoriesNames)
        {
            allCategories.add(new BookCategory(ID++,name));
        }

        return allCategories.get(Integer.parseInt(id));
    }
}
