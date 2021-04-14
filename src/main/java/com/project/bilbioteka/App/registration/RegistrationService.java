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

//        User user = new User(request.getName(),
//                request.getEmail(),
//                request.getPassword(),
//                UserRole.USER);
////
//        System.out.println("//////////////RegistrationService.java////////////////////////");
//        System.out.println("Register/User/name: " + user.getName());
//        System.out.println("Register/User/mail: " + user.getEmail());
//        System.out.println("Register/User/password: " + user.getPassword());
//        System.out.println("Register/request/name: " + request.getName());
//        System.out.println("Register/request/mail: " + request.getEmail());
//        System.out.println("Register/request/password: " + request.getPassword());
//
//        System.out.println("/////////////////////////////////////");

        return userService.signUpUser(new User(request.getName(),
                request.getEmail(),
                request.getPassword(),
                UserRole.USER));
    }
}

