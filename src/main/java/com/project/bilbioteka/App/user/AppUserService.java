package com.project.bilbioteka.App.user;

import com.project.bilbioteka.App.registration.token.ConfirmationToken;
import com.project.bilbioteka.App.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    @Autowired
    private final AppUserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user with email "+ email + " not found"));
    }

    public String signUpUser(AppUser user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if(userExists) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public AppUser findAppUserByEmail(String email) {
        boolean userExists = userRepository.findByEmail(email).isPresent();
        if(!userExists) {
            throw new IllegalStateException("email not shown in database");
        }
        return userRepository.findByEmail(email).get();
    }

    public AppUser findAppUserByName(String name) {
        boolean userExists = userRepository.findByName(name).isPresent();
        if(!userExists) {
            throw new IllegalStateException("name not shown in database");
        }
        return userRepository.findByName(name).get();
    }

    public List<AppUser> getAllUsers() {
        List<AppUser> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        return users;
    }

    public AppUser getUser(String id) {
        return userRepository.findById(Long.parseLong(id));
    }

    public void addUser(AppUser user) {
        userRepository.save(user);
    }

    public void updateUser(AppUser user, String id) {
        AppUser updatedUser = userRepository.findById(Long.parseLong(id));
        updatedUser.setUserName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setId(user.getId());
        updatedUser.setLocked(user.getLocked());
        updatedUser.setEnabled(user.getEnabled());
        updatedUser.setRole(user.getRole());
        userRepository.save(updatedUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(Long.parseLong(id));
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }

    public boolean checkIfValidOldPassword(final AppUser user, final String oldPassword) {
        return bCryptPasswordEncoder.matches(oldPassword, user.getPassword());
    }

    public void changeUserPassword(final AppUser user, final String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    public void updateResetPasswordToken(String token, String email){
        AppUser user = userRepository.findUserByEmail(email);
        if (userRepository.findByEmail(email).isPresent()) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else throw new IllegalStateException("user not found in database");
    }

    public AppUser getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }
}
