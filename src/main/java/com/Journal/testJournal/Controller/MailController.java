package com.Journal.testJournal.Controller;

import com.Journal.testJournal.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    public String sendMail(){
        emailService.sendMail(
                "jayswalankit94@gmail.com",
                "Test",
                "This is a test message with body content."
        );
        return "Mail Sent";
    }
}
