package com.emailserver.emailserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.emailserver.emailserver.service.EmailService;

@Configuration
@EnableScheduling
public class ScheduledSpamConfig {
    
    @Autowired
    private EmailService emailService;

    /*
     * Read - At a predefined time all the messages containing the address carl@gbtec.com will be marked as SPAM 
     */
    @Scheduled(cron = "${cron.expression.value}")
    public void scheduleMarkingEmailsAsSpam() {
        this.emailService.markEmailsAsSpam("carl@gbtec.com");
    }
    
}
