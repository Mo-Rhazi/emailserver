package com.emailserver.emailserver.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.emailserver.emailserver.shared.State;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@Entity
@Table
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        name = "EMAIL_ID",
        nullable = false, 
        updatable = false
    )
    private Long emailId;

    @Column(name = "EMAIL_FROM")
    private String emailFrom;

    @ElementCollection
    @Column(name = "EMAIL_TO")
    private List<String> emailTo = new ArrayList<>();

    @ElementCollection
    @Column(name = "EMAIL_CC")
    private List<String> emailCc = new ArrayList<>();

    @Column(name = "EMAIL_OBJECT")
    private String emailObject;

    @Column(name = "EMAIL_BODY")
    private String emailBody;

    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;    

    @Column(name = "EMAIL_STATE")
    private String emailState;

    public Email(
        String emailFrom,
        List<String> emailTo,
        List<String> emailCc,
        String emailObject,
        String emailBody,
        String emailState) {
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.emailCc = emailCc;
        this.emailObject = emailObject;
        this.emailBody = emailBody;
        this.emailState = emailState;
    }

    public void setEmailState(String emailState) {
        String state = emailState.toUpperCase();
        try {
            State.valueOf(state);
            this.emailState = state;
        } catch (IllegalArgumentException e) {
            log.debug("Just the folowing states are allowed [DRAFT, SENT, DELETED, SPAM]");
        }
    }

    public void addReceiver(String emailTo) {
        if(!this.emailTo.contains(emailTo)) {
            this.emailTo.add(emailTo);
        }
    }

    public void addCc(String cc) {
        if(!this.emailCc.contains(cc)) {
            this.emailCc.add(cc);
        }
    }

    public void markAsSpam() {
        this.emailState = State.SPAM.toString();
    }

    public void markAsDeleted() {
        this.emailState = State.DELETED.toString();
    }

    public void markAsDraft() {
        this.emailState = State.DRAFT.toString();
    }

    public void markAsSent() {
        this.emailState = State.SENT.toString();
    }
}
