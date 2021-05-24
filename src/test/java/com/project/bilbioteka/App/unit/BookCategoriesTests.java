package com.project.bilbioteka.App.unit;

import com.project.bilbioteka.App.book.BookCategoriesENUM;
import com.project.bilbioteka.App.book.BookCategory;
import com.project.bilbioteka.App.book.BookCategoryConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookCategoriesTests {

    @Test
    public void enumContainsAllCategories() {
        List<BookCategoriesENUM> list = BookCategoriesENUM.getBookCategoriesArray();

        for(BookCategoriesENUM category : BookCategoriesENUM.values()) {
            assertTrue(list.stream().anyMatch(o -> o.name().equals(category.toString())));
        }

        /*assertTrue(list.stream().anyMatch(o -> o.name().equals("ACTION")));
        assertTrue(list.stream().anyMatch(o -> o.name().equals("ADVENTURE")));
        assertTrue(list.stream().anyMatch(o -> o.name().equals("CRIMINAL")));
        assertTrue(list.stream().anyMatch(o -> o.name().equals("EDUCATION")));
        assertTrue(list.stream().anyMatch(o -> o.name().equals("HISTORY")));
        assertTrue(list.stream().anyMatch(o -> o.name().equals("HORROR")));
        assertTrue(list.stream().anyMatch(o -> o.name().equals("POETRY")));
        assertTrue(list.stream().anyMatch(o -> o.name().equals("ROMANCE")));
        assertTrue(list.stream().anyMatch(o -> o.name().equals("SCI_FI")));
        assertTrue(list.stream().anyMatch(o -> o.name().equals("THRILLER")));*/
    }

    @Test
    public void BookCategoryObjects() {
        BookCategory bookCategory1 = new BookCategory(1L, BookCategoriesENUM.ACTION);
        assertEquals(1L, bookCategory1.getId());
        assertEquals(BookCategoriesENUM.ACTION, bookCategory1.getName());

        BookCategory bookCategory2 = new BookCategory(732312312L, BookCategoriesENUM.EDUCATION);
        assertEquals(732312312L, bookCategory2.getId());
        assertEquals(BookCategoriesENUM.EDUCATION, bookCategory2.getName());
    }

    @Test
    public void BookCategoryConverter() {
        BookCategoryConverter converter = new BookCategoryConverter();

        int i = 0;
        for(BookCategoriesENUM category : BookCategoriesENUM.values()) {
            assertEquals(category, converter.convert(String.valueOf(i)).getName());
            i++;
        }

        /*assertEquals(BookCategoriesENUM.ACTION, converter.convert("0").getName());
        assertEquals(BookCategoriesENUM.ADVENTURE, converter.convert("1").getName());
        assertEquals(BookCategoriesENUM.CRIMINAL, converter.convert("2").getName());
        assertEquals(BookCategoriesENUM.EDUCATION, converter.convert("3").getName());
        assertEquals(BookCategoriesENUM.HISTORY, converter.convert("4").getName());
        assertEquals(BookCategoriesENUM.HORROR, converter.convert("5").getName());
        assertEquals(BookCategoriesENUM.POETRY, converter.convert("6").getName());
        assertEquals(BookCategoriesENUM.ROMANCE, converter.convert("7").getName());
        assertEquals(BookCategoriesENUM.SCI_FI, converter.convert("8").getName());
        assertEquals(BookCategoriesENUM.THRILLER, converter.convert("9").getName());*/
    }

}
