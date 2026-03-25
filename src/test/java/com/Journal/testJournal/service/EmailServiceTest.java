package com.Journal.testJournal.service;


import com.Journal.testJournal.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    EmailService emailService;

    @Test
    public void testEmail(){
        emailService.sendMail("jayswalankit94@gmail.com","Testing","This is a test message with body content");
    }
}
