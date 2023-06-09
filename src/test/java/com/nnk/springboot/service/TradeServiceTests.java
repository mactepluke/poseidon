package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TradeServiceTests {

    @Autowired
    private ITradeService tradeService;

    @Test
    void tradeServiceTest() {
        Trade trade = new Trade("Trade Account", "Type");

        // Save
        trade = tradeService.save(trade);
        assertNotNull(trade.getTradeId());
        assertEquals("Trade Account", trade.getAccount());

        // Update
        trade.setAccount("Trade Account Update");
        trade = tradeService.update(trade.getTradeId(), trade);
        assertEquals("Trade Account Update", trade.getAccount());

        // Find
        List<Trade> listResult = tradeService.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = trade.getTradeId();
        tradeService.delete(id);
        assertThrows(IllegalArgumentException.class, () -> tradeService.findById(id));
    }
}
