package com.nnk.springboot.controller;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CurveController {

    private static final String DEFAULT_REDIRECTION = "redirect:/curvePoint/list";
    private static final String MODEL_PLURAL_ATTRIBUTE = "curvePoints";
    private static final String MODEL_SINGULAR_ATTRIBUTE = "curvePoint";

    @Autowired
    CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, curvePointService.findAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePoint(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            curvePointService.save(curvePoint);
            model.addAttribute(MODEL_PLURAL_ATTRIBUTE, curvePointService.findAll());
            return DEFAULT_REDIRECTION;
        }

        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        CurvePoint curvePoint = curvePointService.findById(id);
        model.addAttribute(MODEL_SINGULAR_ATTRIBUTE, curvePoint);

        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                                   BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePointService.update(id, curvePoint);
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, curvePointService.findAll());

        return DEFAULT_REDIRECTION;
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {

        curvePointService.delete(id);
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, curvePointService.findAll());

        return DEFAULT_REDIRECTION;
    }
}
