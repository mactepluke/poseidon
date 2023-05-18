package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradeService implements ITradeService {

    @Autowired
    TradeRepository tradeRepository;

    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Trade update(Integer id, Trade trade) {

        if (findById(id) != null) {
            tradeRepository.save(trade);
            return trade;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Trade findById(Integer id) {
        return tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Trade delete(Integer id) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeRepository.delete(trade);
        return trade;
    }
}
