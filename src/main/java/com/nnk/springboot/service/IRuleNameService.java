package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRuleNameService {
    @Transactional(readOnly = true)
    List<RuleName> findAll();

    @Transactional(isolation = Isolation.SERIALIZABLE)
    RuleName save(RuleName ruleName);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    RuleName update(Integer id, RuleName ruleName);

    @Transactional(readOnly = true)
    RuleName findById(Integer id);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    RuleName delete(Integer id);
}
