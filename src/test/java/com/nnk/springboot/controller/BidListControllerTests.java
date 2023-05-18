package com.nnk.springboot.controller;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
class BidListControllerTests {

    private static final int ID = 1;
    private BidList bidList;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BidListService bidListService;

    @BeforeAll
    void setUp() {
        bidList = new BidList();
    }

    @Test
    void bidListEndpointTest() throws Exception {

        when(bidListService.findById(ID)).thenReturn(bidList);
        when(bidListService.update(ID, bidList)).thenReturn(bidList);
        when(bidListService.delete(ID)).thenReturn(bidList);

        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/bidList/validate"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/bidList/update/" + ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/bidList/update/" + ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/bidList/delete/" + ID))
                .andExpect(status().isFound());
    }
}
