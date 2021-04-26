package com.project.bilbioteka.App.user;

import com.project.bilbioteka.App.registration.token.ConfirmationToken;
import com.project.bilbioteka.App.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

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
        updatedUser.setPassword(user.getPassword());
        userRepository.save(updatedUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(Long.parseLong(id));
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}
