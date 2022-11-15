package com.emailserver.emailserver.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emailserver.emailserver.exception.EmailNotFoundException;
import com.emailserver.emailserver.exception.CanNotUpdateEmailException;
import com.emailserver.emailserver.model.Email;
import com.emailserver.emailserver.repository.EmailRespository;
import com.emailserver.emailserver.shared.State;

@Service
public class EmailService {

    @Autowired
    private EmailRespository emailRepository;

    /*
     * Read - Get email by id
     * @Param - Email id
     * @Return - The corresponding email
     */
    public Email getEmailById(Long id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new EmailNotFoundException("Email by id " + id + " was not found"));
    }

    /*
     * @Read - Get all Emails
     * @Return - A list of emails
     */
    public List<Email> getAllEmails() {
        return (List<Email>)this.emailRepository.findAll();
    }

    /*
     * Read - Insert an email into the database
     * @Param - Email Object
     * @Return - The created Email
     */
    public Email addEmail(Email email) {
        return this.emailRepository.save(email);
    }

    /*
     * @Read - Adds a list of emails to the database
     */
    public List<Email> addEmails(List<Email> emails) {
        return (List<Email>)this.emailRepository.saveAll(emails);
    }

    /*
     * @Read - Updated the email body
     * @Param - The email id and the email body
     * @Return - The updated email
     */
    public Email updateEmail(Long emailId, String emailBody) {
        Email currentEmail = getEmailById(emailId);
        if(currentEmail.getEmailState().equals(State.DRAFT.toString())) {
            currentEmail.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            currentEmail.setEmailBody(emailBody);
            return this.emailRepository.save(currentEmail);
        } else {
            throw(new CanNotUpdateEmailException("Only DRAFT emails could be updated"));
        }
    }

    /*
     * @Read - Marks an email as deleted
     * @Param - The email id
     * @Return - The email marked as deleted
     */
    public Email deleteEmailById(Long id) {
        Email deletedEmail = this.getEmailById(id);
        deletedEmail.setEmailState(State.DELETED.toString());
        return deletedEmail;
    }

    /*
     * @Read - Marks a list of emails as deleted
     * @Param - The list of emails
     * @Return - The list of emails marked as deleted
     */
    public List<Email> deleteEmails(List<Email> emails) {
        List<Email> deletedEmails = new ArrayList<>();
        for(Email email: emails) {
            Long emailId = email.getEmailId();
            Email currentDeletedEmail = this.deleteEmailById(emailId);
            deletedEmails.add(currentDeletedEmail);
        }
        return deletedEmails;
    }

    /*
     * Read -  Mark all the messages containing the given email address as SPAM
     * @Param - Email address
     */
    public void markEmailsAsSpam(String emailAddress) {
        List<Email> allEmails = this.emailRepository.findAll();
        List<Email> spamEmails = new ArrayList<>();
        for(Email e: allEmails) {
            if(e.getEmailFrom().equals(emailAddress) || e.getEmailBody().contains(emailAddress)) {
                e.markAsSpam();
                spamEmails.add(e);
            }
        }
        this.emailRepository.saveAll(spamEmails);
    }
}
