package com.example.topshopapi.services.user;

import com.example.topshopapi.entity.User;
import com.example.topshopapi.entity.UserRole;
import com.example.topshopapi.entity.VerificationToken;
import com.example.topshopapi.exception.EmailFailureException;
import com.example.topshopapi.exception.UserAlreadyExistsException;
import com.example.topshopapi.exception.UserNotVerifiedException;
import com.example.topshopapi.model.LoginBody;
import com.example.topshopapi.model.RegistrationBody;
import com.example.topshopapi.repository.UserRepository;
import com.example.topshopapi.repository.VerificationTokenRepository;
import com.example.topshopapi.services.EmailService;
import com.example.topshopapi.services.EncryptionService;
import com.example.topshopapi.services.JWTService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    private final EncryptionService encryptionService;

    private JWTService jwtService;

    private EmailService emailService;

    // Constructor
    public UserServiceImpl(EncryptionService encryptionService, JWTService jwtService, EmailService emailService) {
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    public String loginUser(LoginBody loginBody) throws UserNotVerifiedException, EmailFailureException {
        Optional<User> userToLogin = Optional.ofNullable(userRepository.findByUsername(loginBody.getUsername()));
        if (userToLogin.isPresent()) {
            User user = userToLogin.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                if (user.isEmailVerified()) {
                    return jwtService.generateJWT(user);
                } else {
                    List<VerificationToken> verificationTokens = user.getVerificationTokens();
                    boolean resendVerificationEmail = verificationTokens.size() == 0 || verificationTokens.get(0).getCreatedTimestamp().before(new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000)));
                    // If there is no tokens in list of verificationTokens or if newest token in list was created before an hour ago
                    // create another verification token and resend verification email to user.
                    if (resendVerificationEmail) {
                        VerificationToken verificationToken = createVerificationToken(user);
                        verificationTokenRepository.save(verificationToken);
                        emailService.sendVerificationEmail(verificationToken);
                    }
                    throw new UserNotVerifiedException(resendVerificationEmail);
                }
            }
        }
        return null;
    }

    private VerificationToken createVerificationToken(User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationJWT(user));
        verificationToken.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(user);
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }

    @Transactional
    @Override
    public boolean verifyUser(String token) {
        // Looking for token in DB.
        Optional<VerificationToken> opToken = verificationTokenRepository.findByToken(token);
        if (opToken.isPresent()) {
            VerificationToken verificationToken = opToken.get();
            User user = verificationToken.getUser();
            if (!user.isEmailVerified()) {
                user.setEmailVerified(true);
                userRepository.save(user);
                verificationTokenRepository.deleteByUser(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public User registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException, EmailFailureException {
        if (userRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
                || userRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        user.setMobile(registrationBody.getMobile());
        user.setRole(UserRole.USER);
        // Creating verification token for user to send for email verification.
        VerificationToken verificationToken = createVerificationToken(user);
        // Sending verification email.
        emailService.sendVerificationEmail(verificationToken);
        // Saving user to DB.
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
