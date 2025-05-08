package com.example.emailservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailDto {
    private String email;
    private String subject;
    private String to;
    private String from;

    @Override
    public String toString() {
        return "SendEmailDto{" +
                "email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
