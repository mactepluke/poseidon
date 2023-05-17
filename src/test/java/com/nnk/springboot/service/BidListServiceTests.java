package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.IBidListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class BidTests {

    @Autowired
    private IBidListService bidListService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IBidListService mockBidListService;

    @Test
    void bidListEndpointTest() throws Exception {

        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/bidList/validate"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/bidList/update/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/bidList/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    void bidListServiceTest() {
        BidList bid = new BidList("Account Test", "Type Test", 10d);

        // Save
        bid = bidListService.save(bid);
        assertNotNull(bid.getBidListId());
        assertEquals(10d, bid.getBidQuantity(), 10d);

        // Update
        bid.setBidQuantity(20d);
        bid = bidListService.save(bid);
        assertEquals(20d, bid.getBidQuantity(), 20d);

        // Find
        List<BidList> listResult = bidListService.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = bid.getBidListId();
        bidListService.delete(id);
        assertThrows(IllegalArgumentException.class, () -> bidListService.findById(id));
    }
}
