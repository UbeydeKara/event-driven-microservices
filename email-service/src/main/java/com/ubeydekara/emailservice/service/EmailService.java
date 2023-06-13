package com.ubeydekara.emailservice.service;

import com.ubeydekara.basedomain.payload.EmailPayload;
import com.ubeydekara.emailservice.model.Email;

import java.util.List;

public interface EmailService {
    List<Email> getAll();

    Email send(EmailPayload emailPayload);
}
