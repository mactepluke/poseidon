package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.IRatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RatingTests {

    @Autowired
    private IRatingService ratingService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void ratingServiceTest() {
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

        // Save
        rating = ratingService.save(rating);
        assertNotNull(rating.getId());
        assertEquals(10, (int) rating.getOrderNumber());

        // Update
        rating.setOrderNumber(20);
        rating = ratingService.save(rating);
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
