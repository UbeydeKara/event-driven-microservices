package com.ubeydekara.email.controller;

import com.ubeydekara.base.payload.EmailPayload;
import com.ubeydekara.base.response.ResponseHandler;
import com.ubeydekara.email.model.Email;
import com.ubeydekara.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping
    public ResponseEntity<Object> getEmailList() {
        List<Email> emailList = emailService.getAll();
        return ResponseHandler.generateResponse(HttpStatus.OK, emailList);
    }

    @PostMapping
    public ResponseEntity<Object> createEmail(@RequestBody EmailPayload emailPayload) {
        Email createdEmail = emailService.send(emailPayload);

        if (createdEmail.getId() == null) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while creating the email.");
        }

        return ResponseHandler.generateResponse(HttpStatus.OK, createdEmail, "Email just sent.");
    }
}
