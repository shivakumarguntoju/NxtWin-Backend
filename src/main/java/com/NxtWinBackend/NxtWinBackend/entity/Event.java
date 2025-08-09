package com.NxtWinBackend.NxtWinBackend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "yes_price", precision = 4, scale = 2)
    private Double yesPrice = 5.0;

    @Column(name = "no_price", precision = 4, scale = 2)
    private Double noPrice = 5.0;

    @Column(name = "status", length = 20)
    private String status = "ACTIVE";

    @Column(name = "outcome")
    private Boolean outcome;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "trader_count")
    private Integer traderCount = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors, getters, and setters
    public Event() {
    }

    public Event(String title) {
        this.title = title;
    }

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getYesPrice() {
        return yesPrice;
    }

    public void setYesPrice(Double yesPrice) {
        this.yesPrice = yesPrice;
    }

    public Double getNoPrice() {
        return noPrice;
    }

    public void setNoPrice(Double noPrice) {
        this.noPrice = noPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getOutcome() {
        return outcome;
    }

    public void setOutcome(Boolean outcome) {
        this.outcome = outcome;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getTraderCount() {
        return traderCount;
    }

    public void setTraderCount(Integer traderCount) {
        this.traderCount = traderCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}