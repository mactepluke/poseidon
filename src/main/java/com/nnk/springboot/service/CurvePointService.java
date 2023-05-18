package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repository.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CurvePointService implements ICurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    @Override
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CurvePoint save(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CurvePoint update(Integer id, CurvePoint curvePoint) {

        if (findById(id) != null) {
            curvePointRepository.save(curvePoint);
            return curvePoint;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public CurvePoint findById(Integer id) {
        return curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CurvePoint delete(Integer id) {
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        curvePointRepository.delete(curvePoint);
        return curvePoint;
    }
}
