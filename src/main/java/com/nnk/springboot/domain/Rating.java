package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 125)
    @Size(max = 125)
    private String moodysRating;
    @Column(length = 125)
    @Size(max = 125)
    private String sandpRating;
    @Column(length = 125)
    @Size(max = 125)
    private String fitchRating;
    @Column
    @Positive(message = "Must be positive")
    @NotNull(message = "Order number is mandatory")
    private Integer orderNumber;

    public Rating() {
        // Empty constructor is used by JPA to create entities
    }

    public Rating(String moodysRating, String sandpRating, String fitchRating, Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandpRating = sandpRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandpRating() {
        return sandpRating;
    }

    public void setSandpRating(String sandPRating) {
        this.sandpRating = sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
