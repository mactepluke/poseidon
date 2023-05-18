package com.nnk.springboot.controller;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RuleNameControllerTests {

    private static final int ID = 1;
    private RuleName ruleName;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RuleNameService ruleNameService;

    @BeforeAll
    void setUp() {
        ruleName = new RuleName();
    }

    @Test
    void ruleNameEndpointTest() throws Exception {

        when(ruleNameService.findById(ID)).thenReturn(ruleName);
        when(ruleNameService.update(ID, ruleName)).thenReturn(ruleName);
        when(ruleNameService.delete(ID)).thenReturn(ruleName);

        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/ruleName/validate"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/ruleName/update/" + ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/ruleName/update/" + ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/ruleName/delete/" + ID))
                .andExpect(status().isFound());
    }
}
