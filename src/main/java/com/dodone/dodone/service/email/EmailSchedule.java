package com.dodone.dodone.service.email;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;

@Service
public class EmailSchedule {

    private final TodoEmailScheduler emailScheduler;

    private final int sec = 1000;

    public EmailSchedule( TodoEmailScheduler emailScheduler) {
        this.emailScheduler = emailScheduler;
    }

    @Scheduled(fixedDelay = sec)
    public void sendEmailSpecificTime() throws MessagingException {
        emailScheduler.sendEmail();
    }


}
