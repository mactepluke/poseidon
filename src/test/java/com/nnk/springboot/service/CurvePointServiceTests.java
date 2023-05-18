package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurvePointServiceTests {

    @Autowired
    private ICurvePointService curvePointService;

    @Test
    void curvePointServiceTest() {
        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

        // Save
        curvePoint = curvePointService.save(curvePoint);
        assertNotNull(curvePoint.getId());
        assertEquals(10, (int) curvePoint.getCurveId());

        // Update
        curvePoint.setCurveId(20);
        curvePoint = curvePointService.update(curvePoint.getId(), curvePoint);
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
