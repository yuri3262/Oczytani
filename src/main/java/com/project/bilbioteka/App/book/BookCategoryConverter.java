package com.project.bilbioteka.App.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookCategoryConverter implements Converter<String, BookCategory> {

    @Override
    public BookCategory convert(String id) {
        System.out.println("converting" + id + ".");

        List<String> allCategoriesNames = BookCategoriesENUM.getBookCategoriesArray();
        List<BookCategory> allCategories = new ArrayList<>();
        long ID = 0;
        for(String name : allCategoriesNames)
        {
            allCategories.add(new BookCategory(ID++,name));
        }

        return allCategories.get(Integer.parseInt(id));
    }
}
