package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating update(Integer id, Rating rating) {

        if (findById(id) != null) {
            ratingRepository.save(rating);
            return rating;
        }
        return null;
    }

    public Rating findById(Integer id) {
        return ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
    }

    public Rating delete(Integer id) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingRepository.delete(rating);
        return rating;
    }
}
