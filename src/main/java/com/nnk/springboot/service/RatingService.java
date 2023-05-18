package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RatingService implements IRatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Rating update(Integer id, Rating rating) {

        if (findById(id) != null) {
            ratingRepository.save(rating);
            return rating;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Rating findById(Integer id) {
        return ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Rating delete(Integer id) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingRepository.delete(rating);
        return rating;
    }
}
