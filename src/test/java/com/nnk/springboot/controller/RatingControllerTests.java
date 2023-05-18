package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RatingControllerTests {

    private static final int ID = 1;
    private Rating rating;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RatingService ratingService;

    @BeforeAll
    void setUp() {
        rating = new Rating();
    }

    @Test
    void ratingEndpointTest() throws Exception {

        when(ratingService.findById(ID)).thenReturn(rating);
        when(ratingService.update(ID, rating)).thenReturn(rating);
        when(ratingService.delete(ID)).thenReturn(rating);

        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/rating/validate"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/rating/update/" + ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rating/update/" + ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rating/delete/" + ID))
                .andExpect(status().isFound());
    }
}
