package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.ITradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TradeTests {

    @Autowired
    private ITradeService tradeService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void tradeServiceTest() {
        Trade trade = new Trade("Trade Account", "Type");

        // Save
        trade = tradeService.save(trade);
        assertNotNull(trade.getTradeId());
        assertEquals("Trade Account", trade.getAccount());

        // Update
        trade.setAccount("Trade Account Update");
        trade = tradeService.save(trade);
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
