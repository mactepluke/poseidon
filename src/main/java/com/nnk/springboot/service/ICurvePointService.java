package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICurvePointService {
    List<CurvePoint> findAll();

    @Transactional(isolation = Isolation.SERIALIZABLE)
    CurvePoint save(CurvePoint curvePoint);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    CurvePoint update(Integer id, CurvePoint curvePoint);

    @Transactional(readOnly = true)
    CurvePoint findById(Integer id);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    CurvePoint delete(Integer id);
}
