package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repository.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RuleNameService implements IRuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    @Override
    @Transactional(readOnly = true)
    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public RuleName save(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public RuleName update(Integer id, RuleName ruleName) {

        if (findById(id) != null) {
            ruleNameRepository.save(ruleName);
            return ruleName;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public RuleName findById(Integer id) {
        return ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public RuleName delete(Integer id) {
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        ruleNameRepository.delete(ruleName);
        return ruleName;
    }
}
