package com.ubeydekara.email.service;

import com.ubeydekara.base.payload.EmailPayload;
import com.ubeydekara.email.model.Email;
import com.ubeydekara.email.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final EmailRepository emailRepository;

    @Value("${spring.mail.username}")
    private String sender;

    public List<Email> getAll() {
        return emailRepository.findAll();
    }

    @KafkaListener(topics = "email", groupId = "order")
    public Email send(EmailPayload emailPayload)
    {
        try {
            Email emailDetails = create(emailPayload);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(emailDetails.getEmail_from());
            mailMessage.setTo(emailDetails.getEmail_to());
            mailMessage.setText(emailDetails.getBody());
            mailMessage.setSubject(emailDetails.getSubject());
            javaMailSender.send(mailMessage);

            log.info("Email just sent.");
            return emailRepository.save(emailDetails);
        }
        catch (Exception e) {
            log.error("Email sending has been failed with this message: " + e.getMessage());
            return Email.builder().build();
        }
    }

    private Email create(EmailPayload emailPayload) {
        return Email.builder()
                .email_from(sender)
                .email_to(emailPayload.getEmailTo())
                .subject("Order Created")
                .body(
                        String.format("Your order %s (%s) has been successfully received.",
                                emailPayload.getProduct(),
                                emailPayload.getOrderID())
                )
                .dateTime(LocalDateTime.now())
                .build();
    }

}
