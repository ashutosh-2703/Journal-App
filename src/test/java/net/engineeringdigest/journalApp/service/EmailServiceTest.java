package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail() {
        emailService.sendEmail("sunki.venkat@sugarfit.com","Hola","Hi! How are you?");
    }
}
