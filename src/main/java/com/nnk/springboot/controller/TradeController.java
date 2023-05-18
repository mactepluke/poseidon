package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {

    private static final String DEFAULT_REDIRECTION = "redirect:/trade/list";
    private static final String MODEL_PLURAL_ATTRIBUTE = "trades";
    private static final String MODEL_SINGULAR_ATTRIBUTE = "trade";

    @Autowired
    TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
        log.info("Get request received: display trade list");

        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, tradeService.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade trade) {
        log.info("Get request received: add trade");

        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {

        log.info("Post request received: validate trade");

        if (!result.hasErrors()) {
            if (tradeService.save(trade) != null) {
                log.info("Trade successfully saved");
            }
            model.addAttribute(MODEL_PLURAL_ATTRIBUTE, tradeService.findAll());
            return DEFAULT_REDIRECTION;
        }
        log.error("Could not validate trade");

        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        log.info("Get request received: show trade update form");

        Trade trade = tradeService.findById(id);
        model.addAttribute(MODEL_SINGULAR_ATTRIBUTE, trade);

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {

        log.info("Post request received: update trade");

        if (result.hasErrors()) {
            log.error("Could not update trade");

            return "trade/update";
        }
        if (tradeService.update(id, trade) != null) {
            log.info("Trade successfully updated");
        }
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, tradeService.findAll());

        return DEFAULT_REDIRECTION;
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {

        log.info("Get request received: delete trade");

        if (tradeService.delete(id) != null) {
            log.info("Trade successfully deleted");
        }
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, tradeService.findAll());

        return DEFAULT_REDIRECTION;
    }
}
