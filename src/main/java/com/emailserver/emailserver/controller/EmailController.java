package com.emailserver.emailserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emailserver.emailserver.model.Email;
import com.emailserver.emailserver.service.EmailService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    /*
     * Read - Get all emails from the database
     * @Return - A list of emails
     */
    @GetMapping("/emails")
    public ResponseEntity<List<Email>> getAllEmails() {
        List<Email> emails = this.emailService.getAllEmails();
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }

    /*
     * Read - Get an email by a given id from the database
     * @Param - The email id
     * @Return - The email that has the same ID as the ID specified in the parameter
     */
    @GetMapping("{id}")
    public ResponseEntity<Email> getEmailById(@PathVariable("id") final Long id) {
        Email email = this.emailService.getEmailById(id);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

    /*
     * Read - Update an email
     * @Param - The RequestBody containing the new email body
     * @Return - The updated email
     */
    @PutMapping("{id}")
    public ResponseEntity<Email> updateEmail(@PathVariable("id") final Long id, @RequestBody String EmailBody) {
        Email updatedEmail = this.emailService.updateEmail(id, EmailBody);
        return new ResponseEntity<>(updatedEmail, HttpStatus.OK); 
    }

    @PostMapping("")
    public ResponseEntity<Email> addEmail(@RequestBody Email email) {
        Email addedEmail = this.emailService.addEmail(email);
        return new ResponseEntity<>(addedEmail, HttpStatus.CREATED);
    }

    @PostMapping("bulk")
    public ResponseEntity<List<Email>> addEmails(@RequestBody List<Email> emails) {
        List<Email> addedEmails = this.emailService.addEmails(emails);
        return new ResponseEntity<>(addedEmails, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Email> deleteEmail(@PathVariable("id") final Long id) {
        Email deletedEmail = this.emailService.deleteEmailById(id);
        return new ResponseEntity<>(deletedEmail, HttpStatus.OK);
    }

    @DeleteMapping("bulk")
    public ResponseEntity<List<Email>> deleteEmails(@RequestBody List<Email> emails) {
        List<Email> deletedEmails = this.emailService.deleteEmails(emails);
        return new ResponseEntity<>(deletedEmails, HttpStatus.OK);
    }
}
