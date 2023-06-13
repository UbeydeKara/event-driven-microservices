package com.ubeydekara.emailservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "emails")
public class Email {
    @Id
    @GeneratedValue
    private UUID id;
    private String subject;
    private String body;
    private String email_from;
    private String email_to;
    private LocalDateTime dateTime;
}
