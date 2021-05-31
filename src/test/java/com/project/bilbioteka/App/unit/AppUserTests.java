package com.project.bilbioteka.App.unit;

import com.project.bilbioteka.App.book.Book;
import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserRepository;
import com.project.bilbioteka.App.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppUserTests {

    @Resource
    private AppUserRepository userRepository;

    @Test
    public void constructor() {
        String name = "John";
        String email = "johny@doe.com";
        String password = "password";
        UserRole role = UserRole.USER;

        AppUser user = new AppUser(name, email, password, role);

        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    @Test
    public void givenUser_whenSave_thenGetOk() {
        AppUser user = new AppUser("john","johny@doe.com" , "password", UserRole.USER );
        userRepository.save(user);

        Optional<AppUser> optuser2 = userRepository.findByEmail("johny@doe.com");
        assertTrue(optuser2.isPresent());
        AppUser user2 = optuser2.get();
        assertEquals("john", user2.getName());
        assertEquals("password", user2.getPassword());
        assertEquals("johny@doe.com", user2.getEmail());
        assertEquals(UserRole.USER, user2.getRole());
    }
    @Test
    public void givenUser_whenUpdate_thenGetOk() {
        AppUser user = new AppUser("john", "johny@doe.com", "password", UserRole.USER );
        user.setPassword("password2");
        user.setUserName("john2");

        assertEquals("john2", user.getName());
        assertEquals("password2", user.getPassword());
        assertEquals("johny@doe.com", user.getEmail());
        assertEquals(UserRole.USER, user.getRole());
    }

    @Test
    public void addBooks() {
        AppUser user = new AppUser("john", "johny@doe.com", "password", UserRole.USER );

        Book book1 = new Book("title1",null,null,null,0,null,false);
        Book book2 = new Book("title2",null,null,null,0,null,false);
        Book book3 = new Book("title3",null,null,null,0,null,false);
        book1.setId(0L);
        book2.setId(1L);
        book3.setId(2L);

        assertEquals(0, user.getBooks().size());

        user.addPreBook(book1);

        assertEquals(1, user.getBooks().size());
        assertTrue(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book1.getTitle())));
        assertFalse(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book2.getTitle())));
        assertFalse(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book3.getTitle())));

        user.addPreBook(book2);
        user.addPreBook(book3);

        assertEquals(3, user.getBooks().size());
        assertTrue(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book1.getTitle())));
        assertTrue(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book2.getTitle())));
        assertTrue(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book3.getTitle())));
    }

    @Test
    public void removeBooks() {
        AppUser user = new AppUser("john", "johny@doe.com", "password", UserRole.USER );

        Book book1 = new Book("title1",null,null,null,0,null,false);
        Book book2 = new Book("title2",null,null,null,0,null,false);
        Book book3 = new Book("title3",null,null,null,0,null,false);
        book1.setId(0L);
        book2.setId(1L);
        book3.setId(2L);

        assertEquals(0, user.getBooks().size());

        user.addPreBook(book1);
        user.addPreBook(book2);
        user.addPreBook(book3);

        assertEquals(3, user.getBooks().size());
        assertTrue(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book1.getTitle())));
        assertTrue(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book2.getTitle())));
        assertTrue(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book3.getTitle())));

        user.removeBook(book2);

        assertEquals(2, user.getBooks().size());
        assertTrue(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book1.getTitle())));
        assertFalse(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book2.getTitle())));
        assertTrue(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book3.getTitle())));

        user.removeBook(book3);

        assertEquals(1, user.getBooks().size());
        assertTrue(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book1.getTitle())));
        assertFalse(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book2.getTitle())));
        assertFalse(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book3.getTitle())));

        user.removeBook(book1);

        assertEquals(0, user.getBooks().size());
        assertFalse(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book1.getTitle())));
        assertFalse(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book2.getTitle())));
        assertFalse(user.getBooks().stream().anyMatch(o -> o.getTitle().equals(book3.getTitle())));
    }

}

