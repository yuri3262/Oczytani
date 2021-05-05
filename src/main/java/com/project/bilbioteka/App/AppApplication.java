package com.project.bilbioteka.App;

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
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class AppApplication {

	@Autowired
	private AppUserService userService;

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
}
