package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.IRuleNameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RuleTests {

    @Autowired
    private IRuleNameService ruleNameService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void ruleServiceTest() {
        RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        // Save
        rule = ruleNameService.save(rule);
        assertNotNull(rule.getId());
        assertEquals("Rule Name", rule.getName());

        // Update
        rule.setName("Rule Name Update");
        rule = ruleNameService.save(rule);
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
