package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BidListServiceTests {

    @Autowired
    private IBidListService bidListService;

    @Test
    void bidListServiceTest() {
        BidList bid = new BidList("Account Test", "Type Test", 10d);

        // Save
        bid = bidListService.save(bid);
        assertNotNull(bid.getBidListId());
        assertEquals(10d, bid.getBidQuantity(), 10d);

        // Update
        bid.setBidQuantity(20d);
        bid = bidListService.update(bid.getBidListId(), bid);
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
