package com.project.bilbioteka.App.controllers;

import com.project.bilbioteka.App.user.AppUser;
import com.project.bilbioteka.App.user.AppUserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AppUserService userService;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
    return "forgot_password";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            userService.updateResetPasswordToken(token, email);
            //String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token; na produkcji mozna dodac
            String resetPasswordLink = "http://localhost:8080/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        } catch (IllegalStateException exception) {
            model.addAttribute("error", "User does not exist");
        }
        return "forgot_password";
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("resetpassword@biblioteka.com", "Biblioteka Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";
        StringBuilder content = new StringBuilder();
        content.append("<p>Hello,</p>").append("<p>You have requested to reset your password.</p>").append("<p>Click the link below to change your password:</p>")
                .append("<p><a href=\"").append(link).append("\">Change my password</a></p>").append("<br>").append("<p>Ignore this email if you do remember your password, ")
                .append("or you have not made the request.</p>");

        helper.setSubject(subject);

        helper.setText(String.valueOf(content), true);

        mailSender.send(message);
    }


    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        AppUser user = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
        }
        return "reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model, @RequestParam("password") String password){
        String token = request.getParameter("token");

        AppUser user = userService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "reset_password_form";
        } else {
            userService.changeUserPassword(user, password);
            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "reset_password_form";
    }
}
