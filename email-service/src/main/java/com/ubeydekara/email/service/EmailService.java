package com.ubeydekara.email.service;

import com.ubeydekara.base.payload.EmailPayload;
import com.ubeydekara.email.model.Email;

import java.util.List;

public interface EmailService {
    List<Email> getAll();

    Email send(EmailPayload emailPayload);
}
