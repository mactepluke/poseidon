package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repository.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    public RuleName save(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    public RuleName update(Integer id, RuleName ruleName) {

        if (findById(id) != null) {
            ruleNameRepository.save(ruleName);
            return ruleName;
        }
        return null;
    }

    public RuleName findById(Integer id) {
        return ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
    }

    public RuleName delete(Integer id) {
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        ruleNameRepository.delete(ruleName);
        return ruleName;
    }
}
