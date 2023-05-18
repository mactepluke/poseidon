package com.nnk.springboot.controller;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
class CurvePointControllerTests {

    private static final int ID = 1;
    private CurvePoint curvePoint;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CurvePointService curvePointService;

    @BeforeAll
    void setUp() {
        curvePoint = new CurvePoint();
    }

    @Test
    void curvePointEndpointTest() throws Exception {

        when(curvePointService.findById(ID)).thenReturn(curvePoint);
        when(curvePointService.update(ID, curvePoint)).thenReturn(curvePoint);
        when(curvePointService.delete(ID)).thenReturn(curvePoint);

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/curvePoint/validate"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/curvePoint/update/" + ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/curvePoint/update/" + ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/curvePoint/delete/" + ID))
                .andExpect(status().isFound());
    }
}
