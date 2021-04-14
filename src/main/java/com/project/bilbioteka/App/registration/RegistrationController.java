package com.project.bilbioteka.App.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
//        System.out.println("////////////////Registration Controller.java//////////////////////");
//        System.out.println("Request/name: " + request.getName());
//        System.out.println("Request/password: " + request.getPassword());
//        System.out.println("Request/mail: " + request.getEmail());
//        System.out.println("/////////////////////////////////////");
        return registrationService.register(request);
    }

    @GetMapping
    public String signUp() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "    <meta name=\"description\" content=\"\">\n" +
                "    <meta name=\"author\" content=\"\">\n" +
                "    <title>Please sign in</title>\n" +
                "    <link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M\" crossorigin=\"anonymous\">\n" +
                "    <link href=\"https://getbootstrap.com/docs/4.0/examples/signin/signin.css\" rel=\"stylesheet\" crossorigin=\"anonymous\"/>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "     <div class=\"container\">\n" +
                "      <form class=\"form-signin\" method=\"post\" action=\"/api/v1/registration\">\n" +
                "        <h2 class=\"form-signin-heading\">Please sign up</h2>\n" +
                "        <p>\n" +
                "          <label for=\"username\" class=\"sr-only\">Username</label>\n" +
                "          <input type=\"text\" id=\"username\" name=\"username\" class=\"form-control\" placeholder=\"Username\" required autofocus>\n" +
                "        </p>\n" +
                "\t<p>\n" +
                "          <label for=\"email\" class=\"sr-only\">Email</label>\n" +
                "          <input type=\"text\" id=\"email\" name=\"email\" class=\"form-control\" placeholder=\"Email\" required autofocus>\n" +
                "        </p>\n" +
                "        <p>\n" +
                "          <label for=\"password\" class=\"sr-only\">Password</label>\n" +
                "          <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" placeholder=\"Password\" required>\n" +
                "        </p>\n" +
                "        <button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Sign up</button>\n" +
                "      </form>\n" +
                "</div>\n" +
                "</body></html>";
    }

}
