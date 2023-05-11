package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    public Trade update(Integer id, Trade trade) {

        if (findById(id) != null) {
            tradeRepository.save(trade);
            return trade;
        }
        return null;
    }

    public Trade findById(Integer id) {
        return tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
    }

    public Trade delete(Integer id) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeRepository.delete(trade);
        return trade;
    }
}
