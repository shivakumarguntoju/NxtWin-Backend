package com.NxtWinBackend.NxtWinBackend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "predictions")
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "prediction", nullable = false)
    private Boolean prediction;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private Double amount;

    @Column(name = "price_at_time", nullable = false, precision = 4, scale = 2)
    private Double priceAtTime;

    @Column(name = "potential_return", precision = 10, scale = 2)
    private Double potentialReturn;

    @Column(name = "status", length = 20)
    private String status = "ACTIVE";

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors, getters, and setters
    public Prediction() {
    }

    public Prediction(User user, Event event, Boolean prediction, Double amount, Double priceAtTime) {
        this.user = user;
        this.event = event;
        this.prediction = prediction;
        this.amount = amount;
        this.priceAtTime = priceAtTime;
    }

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Boolean getPrediction() {
        return prediction;
    }

    public void setPrediction(Boolean prediction) {
        this.prediction = prediction;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPriceAtTime() {
        return priceAtTime;
    }

    public void setPriceAtTime(Double priceAtTime) {
        this.priceAtTime = priceAtTime;
    }

    public Double getPotentialReturn() {
        return potentialReturn;
    }

    public void setPotentialReturn(Double potentialReturn) {
        this.potentialReturn = potentialReturn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}