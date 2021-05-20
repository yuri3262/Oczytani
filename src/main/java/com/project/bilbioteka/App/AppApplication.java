package com.project.bilbioteka.App;

import com.project.bilbioteka.App.book.Book;
import com.project.bilbioteka.App.book.BookCategory;
import com.project.bilbioteka.App.book.BookCategoriesENUM;
import com.project.bilbioteka.App.book.BookService;
import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserService;
import com.project.bilbioteka.App.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class AppApplication {

	@Autowired
	private AppUserService userService;

	@Autowired
	private BookService bookService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@PostConstruct
	public void createAdminUser() {

		// check if admin user already exists
		List<AppUser> users = userService.getAllUsers();
		for(AppUser user : users) {
			if(user.getName().equals("admin"))
				return;
		}

		AppUser admin = new AppUser("admin", "admin@admin.com", bCryptPasswordEncoder.encode("admin"), UserRole.ADMIN);
		admin.setEnabled(true);
		userService.addUser(admin);
	}

	@PostConstruct
	public void createWorkerUser() {

		// check if admin user already exists
		List<AppUser> users = userService.getAllUsers();
		for(AppUser user : users) {
			if(user.getName().equals("worker"))
				return;
		}

		AppUser worker = new AppUser("worker", "worker@worker.com", bCryptPasswordEncoder.encode("worker"), UserRole.WORKER);
		worker.setEnabled(true);
		userService.addUser(worker);
	}

	@PostConstruct
	public void createMietekUser() {

		// check if admin user already exists
		List<AppUser> users = userService.getAllUsers();
		for(AppUser user : users) {
			if(user.getName().equals("mietek"))
				return;
		}

		AppUser mietek = new AppUser("mietek", "mietek@mietek.com", bCryptPasswordEncoder.encode("mietek"), UserRole.USER);
		mietek.setEnabled(true);
		userService.addUser(mietek);
	}

	@PostConstruct
	public void createDefaultBook() {

		// check if admin user already exists
		List<Book> books = bookService.getAllBooks();
		for(Book book: books) {
			if(book.getTitle().equals("Heri Pota i daj kamienia"))
				return;
		}


		List<BookCategory> allCategories = new ArrayList<>();

		allCategories.add(new BookCategory(1L,BookCategoriesENUM.ADVENTURE));
		allCategories.add(new BookCategory(2L,BookCategoriesENUM.ACTION));
		allCategories.add(new BookCategory(3L,BookCategoriesENUM.ROMANCE));

		Book defaultBook = new Book("Heri Pota i daj kamienia","dzej kej they see me rolling","pwn", allCategories,100,"10-10-1990",true);
		bookService.addBook(defaultBook);



		for(Book book: books) {
			if(book.getTitle().equals("Jakis tytul"))
				return;
		}


		allCategories.clear();
		allCategories.add(new BookCategory(1L,BookCategoriesENUM.ROMANCE));
		allCategories.add(new BookCategory(2L,BookCategoriesENUM.CRIMINAL));

		defaultBook = new Book("Jakis tytul","jakis autor","jakis pub", allCategories,420,"05-05-1980",true);
		bookService.addBook(defaultBook);


		for(Book book: books) {
			if(book.getTitle().equals("Jakis tytul2"))
				return;
		}


		allCategories.clear();
		allCategories.add(new BookCategory(1L,BookCategoriesENUM.SCI_FI));
		allCategories.add(new BookCategory(2L,BookCategoriesENUM.POETRY));

		defaultBook = new Book("Jakis tytul2","jakis autor2","jakis pub2", allCategories,123,"05-05-1234",true);
		bookService.addBook(defaultBook);
	}

}
