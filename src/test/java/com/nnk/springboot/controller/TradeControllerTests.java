package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
class TradeControllerTests {

    private static final int ID = 1;
    private Trade trade;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TradeService tradeService;

    @BeforeAll
    void setUp() {
        trade = new Trade();
    }

    @Test
    void tradeEndpointTest() throws Exception {

        when(tradeService.findById(ID)).thenReturn(trade);
        when(tradeService.update(ID, trade)).thenReturn(trade);
        when(tradeService.delete(ID)).thenReturn(trade);

        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/trade/validate"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/trade/update/" + ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/trade/update/" + ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/trade/delete/" + ID))
                .andExpect(status().isFound());
    }
}
