package com.project.bilbioteka.App.book;

import com.project.bilbioteka.App.user.AppUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    @ElementCollection
    private List<BookCategory> category;
    private int numberOfPages;
    private String dateOfPublication;
    private Boolean isAvailable;
    private Boolean isPreBooked = false;
    private String dateOfBorrow;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser appUser;




    public Book(String title, String author, String publisher, List<BookCategory> category, int numberOfPages, String dateOfPublication, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.numberOfPages = numberOfPages;
        this.dateOfPublication = dateOfPublication;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        System.out.println(super.toString());
        StringBuilder builder = new StringBuilder();
        builder.append("id: ").append(this.id)
                .append("\ntitle: ").append(this.title)
                .append("\nauthor: ").append(this.author)
                .append("\npublisher: ").append(this.publisher)
                .append("\ncategories: ");


        for(BookCategory cat : this.category)
        {
            builder.append("\n").append(cat.getName());
        }

        builder.append("\ndate od publication: ").append(this.dateOfPublication)
                .append("\nis availabe: ").append(this.isAvailable);

        return  builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
