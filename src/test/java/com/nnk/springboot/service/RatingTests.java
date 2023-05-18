package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RatingTests {

    @Autowired
    private IRatingService ratingService;


    @Test
    void ratingServiceTest() {
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

        // Save
        rating = ratingService.save(rating);
        assertNotNull(rating.getId());
        assertEquals(10, (int) rating.getOrderNumber());

        // Update
        rating.setOrderNumber(20);
        rating = ratingService.update(rating.getId(), rating);
        assertEquals(20, (int) rating.getOrderNumber());

        // Find
        List<Rating> listResult = ratingService.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = rating.getId();
        ratingService.delete(id);
        assertThrows(IllegalArgumentException.class, () -> ratingService.findById(id));

    }
}
