package com.project.bilbioteka.App.registration;

import com.project.bilbioteka.App.user.User;
import com.project.bilbioteka.App.user.UserRole;
import com.project.bilbioteka.App.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail){
            throw new IllegalStateException("email invalid");
        }

        return userService.signUpUser(
                new User(request.getUserName(),
                            request.getEmail(),
                            request.getPassword(),
                            UserRole.USER));
    }
}

