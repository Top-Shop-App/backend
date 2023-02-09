-- Static data for test environment.

-- Passwords are in the format: Password<UserLetter>123. Unless specified otherwise.
-- To encrypt password, head to https://www.javainuse.com/onlineBcrypt and enter your plain text password.
-- Make sure the number of salt rounds matches what's in your src/main/resources/application.properties file.

INSERT INTO users (first_name, last_name, email, username, password, mobile, role, email_verified)
    VALUES ('UserA-FirstName', 'UserA-LastName', 'UserA@junit.com', 'UserA', '$2a$10$tx/AXGzBmMmy6KyH2TLrpu7o5h4E3rUbnPa2Oe7yhucNXuvCzTtdO', '1234567890', 'USER', true),
           ('UserB-FirstName', 'UserB-LastName', 'UserB@junit.com', 'UserB', '$2a$10$lcdMhjW5kfLC3SRslPnjp.Ek.A0h8ChYnwgC1tPzoYQxMzeQL8eSS', '0987654321', 'USER', false);