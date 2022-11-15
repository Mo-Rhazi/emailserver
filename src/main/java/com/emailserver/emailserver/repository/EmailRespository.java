package com.emailserver.emailserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emailserver.emailserver.model.Email;

public interface EmailRespository extends JpaRepository<Email, Long> {
    
}
