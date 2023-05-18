package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRatingService {
    List<Rating> findAll();

    @Transactional(isolation = Isolation.SERIALIZABLE)
    Rating save(Rating rating);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    Rating update(Integer id, Rating rating);

    @Transactional(readOnly = true)
    Rating findById(Integer id);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    Rating delete(Integer id);
}
