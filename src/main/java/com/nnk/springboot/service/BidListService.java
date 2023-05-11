package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repository.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService {

    @Autowired
    BidListRepository bidListRepository;

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public BidList save(BidList bid) {
        return bidListRepository.save(bid);
    }

    public BidList update(Integer id, BidList bidList) {

        if (findById(id) != null) {
            bidListRepository.save(bidList);
            return bidList;
        }
        return null;
    }

    public BidList findById(Integer id) {
        return bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
    }

    public BidList delete(Integer id) {
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        bidListRepository.delete(bidList);
        return bidList;
    }
}
