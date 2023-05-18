package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RuleNameServiceTests {

    @Autowired
    private IRuleNameService ruleNameService;

    @Test
    void ruleServiceTest() {
        RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        // Save
        rule = ruleNameService.save(rule);
        assertNotNull(rule.getId());
        assertEquals("Rule Name", rule.getName());

        // Update
        rule.setName("Rule Name Update");
        rule = ruleNameService.update(rule.getId(), rule);
        assertEquals("Rule Name Update", rule.getName());

        // Find
        List<RuleName> listResult = ruleNameService.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = rule.getId();
        ruleNameService.delete(id);
        assertThrows(IllegalArgumentException.class, () -> ruleNameService.findById(id));
    }
}
