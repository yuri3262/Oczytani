package com.project.bilbioteka.App.unit;

import com.project.bilbioteka.App.book.Book;
import com.project.bilbioteka.App.registration.token.ConfirmationTokenRepository;
import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserRepository;
import com.project.bilbioteka.App.user.AppUserService;
import com.project.bilbioteka.App.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppUserServiceTests {

    @Autowired
    private AppUserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Test
    public void givenUser_signUpUser_thenGetOk() {
        AppUser user = new AppUser("john", "john@doe.com", "password", UserRole.USER );

        String token = userService.signUpUser(user);
        AppUser user2 = userService.findAppUserByEmail(user.getEmail());
        assertNotEquals("password", user2.getPassword());
        assertFalse(user2.isEnabled());

        userService.enableUser(user.getEmail());

        AppUser user3 = userService.findAppUserByEmail(user.getEmail());
        assertTrue(user3.isEnabled());

        confirmationTokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void addUsersAndGetAllOfThem_1() {
        userRepository.deleteAll();
        assertEquals(0, userService.getAllUsers().size());
    }

    @Test
    public void addUsersAndGetAllOfThem_2() {
        userRepository.deleteAll();
        assertEquals(0, userService.getAllUsers().size());

        userService.addUser(new AppUser());

        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    public void addUsersAndGetAllOfThem_3() {
        userRepository.deleteAll();
        assertEquals(0, userService.getAllUsers().size());

        userService.addUser(new AppUser());
        userService.addUser(new AppUser());
        userService.addUser(new AppUser());

        assertEquals(3, userService.getAllUsers().size());
    }

    @Test
    public void getUser() {
        userRepository.deleteAll();
        assertEquals(0, userService.getAllUsers().size());

        AppUser user = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        userService.addUser(user);

        assertEquals(1, userService.getAllUsers().size());

        AppUser foundUser = userService.getUser(String.valueOf(user.getId()));

        assertEquals(foundUser.getName(), user.getName());
        assertEquals(foundUser.getEmail(), user.getEmail());
        assertEquals(foundUser.getPassword(), user.getPassword());
        assertEquals(foundUser.getRole(), user.getRole());
    }

    @Test
    public void deleteUser() {
        userRepository.deleteAll();
        assertEquals(0, userService.getAllUsers().size());

        userService.addUser(new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER));

        assertEquals(1, userService.getAllUsers().size());

        userService.deleteUser(String.valueOf(userService.findAppUserByName("user1").getId()));

        assertEquals(0, userService.getAllUsers().size());
    }

    @Test
    public void findUserByEmail() {
        userRepository.deleteAll();
        assertEquals(0, userService.getAllUsers().size());

        AppUser user = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        userService.addUser(user);

        assertEquals(1, userService.getAllUsers().size());

        AppUser foundUser = userService.findAppUserByEmail("user1@mail.com");

        assertEquals(user.getName(), foundUser.getName());
    }

    @Test
    public void changeUserPassword() {
        userRepository.deleteAll();
        assertEquals(0, userService.getAllUsers().size());

        AppUser user = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        userService.addUser(user);

        assertEquals(1, userService.getAllUsers().size());

        String oldPassword = user.getPassword();
        userService.changeUserPassword(user,"pass2");

        assertNotEquals(oldPassword, userService.findAppUserByName(user.getName()).getPassword());
    }

}
