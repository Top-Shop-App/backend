package com.example.topshopapi.service;

import com.example.topshopapi.entity.VerificationToken;
import com.example.topshopapi.exception.EmailFailureException;
import com.example.topshopapi.exception.UserAlreadyExistsException;
import com.example.topshopapi.exception.UserNotVerifiedException;
import com.example.topshopapi.model.LoginBody;
import com.example.topshopapi.model.RegistrationBody;
import com.example.topshopapi.repository.VerificationTokenRepository;
import com.example.topshopapi.services.user.UserServiceImpl;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @RegisterExtension
    private static GreenMailExtension greenMailExtension = new GreenMailExtension(ServerSetup.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("springboot", "secret"))
            .withPerMethodLifecycle(true);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

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

    @Test
    @Transactional
    public void testLoginUser() throws UserNotVerifiedException, EmailFailureException {
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername("UserA-NotExists");
        loginBody.setPassword("PasswordA123-BadPassword");
        Assertions.assertNull(userService.loginUser(loginBody), "The user should not exist.");
        loginBody.setUsername("UserA");
        Assertions.assertNull(userService.loginUser(loginBody), "The password should be incorrect.");
        loginBody.setPassword("PasswordA123");
        Assertions.assertNotNull(userService.loginUser(loginBody), "The user should login successfully.");
        loginBody.setUsername("UserB");
        loginBody.setUsername("PasswordB123");
        try {
            userService.loginUser(loginBody);
            Assertions.assertTrue(false, "User should not have email verified.");
        } catch(UserNotVerifiedException e) {
            Assertions.assertTrue(e.isNewEmailSent(), "Email verification should be sent.");
            Assertions.assertEquals(1, greenMailExtension.getReceivedMessages().length);
        }
        try {
            userService.loginUser(loginBody);
            Assertions.assertTrue(false, "User should not have email verified.");
        } catch(UserNotVerifiedException e) {
            Assertions.assertTrue(e.isNewEmailSent(), "Email verification should not be re-sent.");
            Assertions.assertEquals(1, greenMailExtension.getReceivedMessages().length);
        }
    }

    @Test
    @Transactional
    public void testVerifyUser() throws EmailFailureException {
        Assertions.assertFalse(userService.verifyUser("Bad Token"), "Token that is bad or does not exist should return false.");
        LoginBody body = new LoginBody();
        body.setUsername("UserB");
        body.setPassword("PasswordB123");
        try {
            userService.loginUser(body);
            Assertions.assertTrue(false, "User should not have email verified.");
        } catch(UserNotVerifiedException e) {
            List<VerificationToken> tokens = verificationTokenRepository.findByUserId(2L);
            String token = tokens.get(0).getToken();
            Assertions.assertTrue(userService.verifyUser(token), "Token should be valid.");
            Assertions.assertNotNull(body, "The user should now be verified.");
        }
    }
}
