package com.nnk.springboot.controller;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {

    private static final String DEFAULT_REDIRECTION = "redirect:/bidList/list";
    private static final String MODEL_SINGULAR_ATTRIBUTE = "bidList";
    private static final String MODEL_PLURAL_ATTRIBUTE = "bids";

    @Autowired
    BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        log.info("Get request received: display Bid List");
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBid(BidList bidList) {
        log.info("Get request received: add Bid List");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model) {

        log.info("Post request received: validate Bid List");

        if (!result.hasErrors()) {
            if (bidListService.save(bidList) != null) {
                log.info("Bid List successfully saved");
            }

            model.addAttribute(MODEL_PLURAL_ATTRIBUTE, bidListService.findAll());
            return DEFAULT_REDIRECTION;
        }

        log.error("Could not validate bid list");

        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        log.info("Get request received: show Bid List update form");

        BidList bidList = bidListService.findById(id);

        model.addAttribute(MODEL_SINGULAR_ATTRIBUTE, bidList);

        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {

        log.info("Post request received: update Bid List");

        if (result.hasErrors()) {
            log.error("Could not update bid list");

            return "bidList/update";
        }
        if (bidListService.update(id, bidList) != null) {
            log.info("Bid List successfully updated");
        }

        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, bidListService.findAll());

        return DEFAULT_REDIRECTION;
    }


    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        log.info("Get request received: delete Bid List");

        if (bidListService.delete(id) != null) {
            log.info("Bid List successfully deleted");
        }

        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, bidListService.findAll());

        return DEFAULT_REDIRECTION;
    }


}
