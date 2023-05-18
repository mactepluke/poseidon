package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ITradeService {
    List<Trade> findAll();

    @Transactional(isolation = Isolation.SERIALIZABLE)
    Trade save(Trade trade);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    Trade update(Integer id, Trade trade);

    @Transactional(readOnly = true)
    Trade findById(Integer id);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    Trade delete(Integer id);
}
