package com.nnk.springboot.controller;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class CurveController {

    private static final String DEFAULT_REDIRECTION = "redirect:/curvePoint/list";
    private static final String MODEL_PLURAL_ATTRIBUTE = "curvePoints";
    private static final String MODEL_SINGULAR_ATTRIBUTE = "curvePoint";

    @Autowired
    CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        log.info("Get request received: display curvePoint list");

        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, curvePointService.findAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePoint(CurvePoint curvePoint) {
        log.info("Get request received: add curvePoint");

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

        log.info("Post request received: validate curvePoint");

        if (!result.hasErrors()) {
            if (curvePointService.save(curvePoint) != null) {
                log.info("CurvePoint successfully saved");

            }
            model.addAttribute(MODEL_PLURAL_ATTRIBUTE, curvePointService.findAll());

            return DEFAULT_REDIRECTION;
        }

        log.error("Could not validate curvePoint");

        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        log.info("Get request received: show curvePoint update form");

        CurvePoint curvePoint = curvePointService.findById(id);
        model.addAttribute(MODEL_SINGULAR_ATTRIBUTE, curvePoint);

        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                                   BindingResult result, Model model) {

        log.info("Post request received: update curvePoint");

        if (result.hasErrors()) {
            log.error("Could not update curvePoint");

            return "curvePoint/update";
        }
        if (curvePointService.update(id, curvePoint) != null) {
            log.info("CurvePoint successfully updated");
        }
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, curvePointService.findAll());

        return DEFAULT_REDIRECTION;
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {

        log.info("Get request received: delete curvePoint");

        if (curvePointService.delete(id) != null) {
            log.info("CurvePoint successfully deleted");

        }
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, curvePointService.findAll());

        return DEFAULT_REDIRECTION;
    }
}
