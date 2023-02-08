package com.example.topshopapi.service;

import com.example.topshopapi.exception.UserAlreadyExistsException;
import com.example.topshopapi.model.RegistrationBody;
import com.example.topshopapi.services.user.UserServiceImpl;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @RegisterExtension
    private static GreenMailExtension greenMailExtension = new GreenMailExtension(ServerSetup.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("springboot", "secret"))
            .withPerMethodLifecycle(true);

    @Autowired
    private UserServiceImpl userService;

    @Test
    @Transactional
    public void testRegisterUser() throws MessagingException {
        // Building registration body with a username that is already taken.
        RegistrationBody body = new RegistrationBody();
        body.setUsername("UserA");
        body.setEmail("UserServiceTest$testRegisterUser@junit.com");
        body.setFirstName("FirstName");
        body.setLastName("LastName");
        body.setPassword("MySecretPassword123");
        body.setMobile("1029384756");
        // Verifying that username is going to be rejected.
        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(body), "Username should already be in use.");
        // Setting username to a unique value.
        body.setUsername("UserServiceTest$testRegisterUser");
        // Setting email to existing email.
        body.setEmail("UserA@junit.com");
        // Verifying that email is going to be rejected.
        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(body), "Email should already be in use.");
        // Setting email back to unique value.
        body.setEmail("UserServiceTest$testRegisterUser@junit.com");
        // Verifying that user is now being created successfully.
        Assertions.assertDoesNotThrow(() -> userService.registerUser(body), "User should register successfully.");
        // Checking to see if email to verify user is being sent properly.
        Assertions.assertEquals(body.getEmail(), greenMailExtension.getReceivedMessages()[0]
                .getRecipients(Message.RecipientType.TO)[0].toString());
    }
}
