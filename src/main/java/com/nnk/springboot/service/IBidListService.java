package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IBidListService {
    @Transactional(readOnly = true)
    List<BidList> findAll();

    @Transactional(isolation = Isolation.SERIALIZABLE)
    BidList save(BidList bid);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    BidList update(Integer id, BidList bidList);

    @Transactional(readOnly = true)
    BidList findById(Integer id);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    BidList delete(Integer id);
}
