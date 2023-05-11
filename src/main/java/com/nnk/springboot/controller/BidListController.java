package com.nnk.springboot.controller;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {

    private static final String DEFAULT_REDIRECTION = "redirect:/bidList/list";
    private static final String MODEL_SINGULAR_ATTRIBUTE = "bidList";
    private static final String MODEL_PLURAL_ATTRIBUTE = "bids";

    @Autowired
    BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBid(BidList bidList) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            bidListService.save(bidList);
            model.addAttribute(MODEL_PLURAL_ATTRIBUTE, bidListService.findAll());
            return DEFAULT_REDIRECTION;
        }

        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        BidList bidList = bidListService.findById(id);
        model.addAttribute(MODEL_SINGULAR_ATTRIBUTE, bidList);

        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidListService.update(id, bidList);
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, bidListService.findAll());

        return DEFAULT_REDIRECTION;
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        bidListService.delete(id);
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, bidListService.findAll());

        return DEFAULT_REDIRECTION;
    }
}
