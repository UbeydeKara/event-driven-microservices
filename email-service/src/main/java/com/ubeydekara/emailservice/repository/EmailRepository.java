package com.ubeydekara.emailservice.repository;

import com.ubeydekara.emailservice.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
