package com.maximum.nikonbackend.service;

public interface EmailService {
    /**
     *
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String to, String subject, String content);
}