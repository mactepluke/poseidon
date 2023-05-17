package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.ICurvePointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CurvePointTests {

    @Autowired
    private ICurvePointService curvePointService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void curvePointServiceTest() {
        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

        // Save
        curvePoint = curvePointService.save(curvePoint);
        assertNotNull(curvePoint.getId());
        assertEquals(10, (int) curvePoint.getCurveId());

        // Update
        curvePoint.setCurveId(20);
        curvePoint = curvePointService.save(curvePoint);
        assertEquals(20, (int) curvePoint.getCurveId());

        // Find
        List<CurvePoint> listResult = curvePointService.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = curvePoint.getId();
        curvePointService.delete(id);
        assertThrows(IllegalArgumentException.class, () -> curvePointService.findById(id));
    }

}
