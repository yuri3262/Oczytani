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
    private AppUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

        userService.deleteUserById(user.getId());
    }

    @Test
    public void addUsersAndGetAllOfThem_1() {
        int initialSize = userService.getAllUsers().size();

        AppUser user = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        userService.addUser(user);

        assertEquals(initialSize + 1, userService.getAllUsers().size());

        userService.deleteUserById(user.getId());
    }

    @Test
    public void addUsersAndGetAllOfThem_2() {
        int initialSize = userService.getAllUsers().size();

        AppUser user1 = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        AppUser user2 = new AppUser("user2", "user2@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        AppUser user3 = new AppUser("user3", "user3@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);

        assertEquals(initialSize + 3, userService.getAllUsers().size());

        userService.deleteUserById(user1.getId());
        userService.deleteUserById(user2.getId());
        userService.deleteUserById(user3.getId());
    }

    @Test
    public void getUser() {
        int initialSize = userService.getAllUsers().size();

        AppUser user = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        userService.addUser(user);

        assertEquals(initialSize + 1, userService.getAllUsers().size());

        AppUser foundUser = userService.getUser(String.valueOf(user.getId()));

        assertEquals(foundUser.getName(), user.getName());
        assertEquals(foundUser.getEmail(), user.getEmail());
        assertEquals(foundUser.getPassword(), user.getPassword());
        assertEquals(foundUser.getRole(), user.getRole());

        userService.deleteUserById(user.getId());
    }

    @Test
    public void getByResetPasswordToken() {
        int initialSize = userService.getAllUsers().size();

        AppUser user = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        userService.addUser(user);

        assertEquals(initialSize + 1, userService.getAllUsers().size());

        userService.updateResetPasswordToken("token", user.getEmail());
        user = userService.getUser(String.valueOf(user.getId()));
        AppUser foundUser = userService.getByResetPasswordToken(user.getResetPasswordToken());

        assertEquals(foundUser.getName(), user.getName());
        assertEquals(foundUser.getEmail(), user.getEmail());
        assertEquals(foundUser.getPassword(), user.getPassword());
        assertEquals(foundUser.getRole(), user.getRole());

        userService.deleteUserById(user.getId());
    }

    @Test
    public void updateUser() {
        int initialSize = userService.getAllUsers().size();

        AppUser user = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        userService.addUser(user);

        assertEquals(initialSize + 1, userService.getAllUsers().size());

        user.setUserName("user2");
        user.setEmail("user2@mail.com");
        user.setRole(UserRole.WORKER);
        userService.updateUser(user, String.valueOf(user.getId()));

        AppUser updatedUser = userRepository.findById(user.getId()).get();

        assertEquals(updatedUser.getName(), user.getName());
        assertEquals(updatedUser.getEmail(), user.getEmail());
        assertEquals(updatedUser.getPassword(), user.getPassword());
        assertEquals(updatedUser.getRole(), user.getRole());

        userService.deleteUserById(user.getId());
    }

    @Test
    public void deleteUser() {
        int initialSize = userService.getAllUsers().size();

        AppUser user = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        userService.addUser(user);

        assertEquals(initialSize + 1, userService.getAllUsers().size());

        userService.deleteUser(String.valueOf(user.getId()));

        assertEquals(initialSize, userService.getAllUsers().size());

        userService.deleteUserById(user.getId());
    }

    @Test
    public void enableUser() {
        int initialSize = userService.getAllUsers().size();

        AppUser user = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        userService.addUser(user);

        assertEquals(initialSize + 1, userService.getAllUsers().size());

        assertFalse(user.getEnabled());
        userService.enableUser(user.getEmail());
        user = userService.getUser(String.valueOf(user.getId()));
        assertTrue(user.getEnabled());

        userService.deleteUserById(user.getId());
    }

    @Test
    public void changeUserPassword() {
        int initialSize = userService.getAllUsers().size();

        AppUser user = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        userService.addUser(user);

        assertEquals(initialSize + 1, userService.getAllUsers().size());

        String oldPassword = user.getPassword();
        userService.changeUserPassword(user,"pass2");

        assertNotEquals(oldPassword, userRepository.findById(user.getId()).get().getPassword());

        userService.deleteUserById(user.getId());
    }

    @Test
    public void checkIfValidOldPassword() {
        int initialSize = userService.getAllUsers().size();

        String oldPassword = "pass";
        String newPassword = "pass2";

        AppUser user = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode(newPassword), UserRole.USER);
        userService.addUser(user);

        assertEquals(initialSize + 1, userService.getAllUsers().size());

        assertTrue(userService.checkIfValidOldPassword(user, newPassword));
        assertFalse(userService.checkIfValidOldPassword(user, oldPassword));

        userService.deleteUserById(user.getId());
    }

    @Test
    public void updateResetPasswordToken() {
        int initialSize = userService.getAllUsers().size();

        AppUser user = new AppUser("user1", "user1@mail.com", bCryptPasswordEncoder.encode("pass"), UserRole.USER);
        userService.addUser(user);

        assertEquals(initialSize + 1, userService.getAllUsers().size());

        String token = "token";
        assertEquals(null, user.getResetPasswordToken());
        userService.updateResetPasswordToken(token, user.getEmail());
        AppUser foundUser = userService.getUser(String.valueOf(user.getId()));
        assertEquals(token, foundUser.getResetPasswordToken());

        String token2 = "token2";
        userService.updateResetPasswordToken(token2, user.getEmail());
        foundUser = userService.getUser(String.valueOf(user.getId()));
        assertEquals(token2, foundUser.getResetPasswordToken());

        userService.deleteUserById(user.getId());
    }

}
