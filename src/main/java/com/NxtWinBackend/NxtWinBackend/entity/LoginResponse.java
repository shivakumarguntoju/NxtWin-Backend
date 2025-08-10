package com.NxtWinBackend.NxtWinBackend.entity;

public class LoginResponse {
    private Long userId;
    private String email;
    private String token;
    private String name;

    public LoginResponse(Long userId, String email, String token, String name) {
        this.userId = userId;
        this.email = email;
        this.token = token;
        this.name = name;
    }

    // Getters and setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}