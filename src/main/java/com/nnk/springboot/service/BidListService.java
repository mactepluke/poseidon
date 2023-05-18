package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repository.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BidListService implements IBidListService {

    @Autowired
    BidListRepository bidListRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BidList save(BidList bid) {
        return bidListRepository.save(bid);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BidList update(Integer id, BidList bidList) {

        if (findById(id) != null) {
            bidListRepository.save(bidList);
            return bidList;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public BidList findById(Integer id) {
        return bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BidList delete(Integer id) {
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        bidListRepository.delete(bidList);
        return bidList;
    }
}
