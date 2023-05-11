package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
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
public class RatingController {

    private static final String DEFAULT_REDIRECTION = "redirect:/rating/list";
    private static final String MODEL_PLURAL_ATTRIBUTE = "ratings";
    private static final String MODEL_SINGULAR_ATTRIBUTE = "rating";

    @Autowired
    RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ratingService.findAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRating(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            ratingService.save(rating);
            model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ratingService.findAll());
            return DEFAULT_REDIRECTION;
        }

        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Rating rating = ratingService.findById(id);
        model.addAttribute(MODEL_SINGULAR_ATTRIBUTE, rating);

        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "rating/update";
        }
        ratingService.update(id, rating);
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ratingService.findAll());

        return DEFAULT_REDIRECTION;
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {

        ratingService.delete(id);
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ratingService.findAll());

        return DEFAULT_REDIRECTION;
    }
}
