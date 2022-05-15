package com.example.instant_messanger.messages_data;

public class Messages {
    private final String email;
    private final String message;
    public Messages(String email, String message) {
        this.email = email;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getEmail() {
        return this.email;
    }
}