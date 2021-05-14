package com.project.bilbioteka.App.book;

import javax.persistence.*;

@Embeddable
public class BookCategory {

    private Long id;
    private String name;

    public BookCategory(Long id,String name) {
        this.id = id;
        this.name = name;
    }

    public BookCategory() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookCategory other = (BookCategory) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
