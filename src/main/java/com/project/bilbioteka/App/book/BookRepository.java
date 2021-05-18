package com.project.bilbioteka.App.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book getOne(Long aLong);


//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("DELETE FROM book a WHERE a.id = ?1")
//    int deleteBook(Long id);

}
