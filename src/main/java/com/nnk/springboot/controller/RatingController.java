package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
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
public class RatingController {

    private static final String DEFAULT_REDIRECTION = "redirect:/rating/list";
    private static final String MODEL_PLURAL_ATTRIBUTE = "ratings";
    private static final String MODEL_SINGULAR_ATTRIBUTE = "rating";

    @Autowired
    RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model) {
        log.info("Get request received: display rating list");

        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ratingService.findAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRating(Rating rating) {
        log.info("Get request received: add rating");

        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {

        log.info("Post request received: validate rating");

        if (!result.hasErrors()) {
            if (ratingService.save(rating) != null) {
                log.info("Rating successfully saved");
            }
            model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ratingService.findAll());
            return DEFAULT_REDIRECTION;
        }

        log.error("Could not validate rating");

        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        log.info("Get request received: show rating update form");

        Rating rating = ratingService.findById(id);
        model.addAttribute(MODEL_SINGULAR_ATTRIBUTE, rating);

        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {

        log.info("Post request received: update rating");

        if (result.hasErrors()) {
            log.error("Could not update rating");

            return "rating/update";
        }
        if (ratingService.update(id, rating) != null) {
            log.info("Rating successfully updated");

        }
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ratingService.findAll());

        return DEFAULT_REDIRECTION;
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {

        log.info("Get request received: delete rating");

        if (ratingService.delete(id) != null) {
            log.info("Rating successfully deleted");
        }
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ratingService.findAll());

        return DEFAULT_REDIRECTION;
    }
}
