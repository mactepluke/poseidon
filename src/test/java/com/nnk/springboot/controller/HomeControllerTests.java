package com.nnk.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class HomeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeEndpointTest() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/admin/home"))
                .andExpect(status().isFound());
    }
}
