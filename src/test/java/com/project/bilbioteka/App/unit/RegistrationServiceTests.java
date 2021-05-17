package com.project.bilbioteka.App.unit;

import com.project.bilbioteka.App.registration.RegistrationRequest;
import com.project.bilbioteka.App.registration.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RegistrationServiceTests {

    @Resource
    RegistrationService registrationService;

    @Test
    public void givenRequest_whenRegister_thenGetOk() {
        RegistrationRequest registrationRequest = new RegistrationRequest("john", "john2@doe.com", "password");
        String token = registrationService.register(registrationRequest);

        assertEquals("confirmed", registrationService.confirmToken(token));
        assertTrue(registrationService.isConfirmed(token));

    }


}

