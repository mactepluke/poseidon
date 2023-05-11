package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {

    private static final String DEFAULT_REDIRECTION = "redirect:/trade/list";
    private static final String MODEL_PLURAL_ATTRIBUTE = "trades";
    private static final String MODEL_SINGULAR_ATTRIBUTE = "trade";

    @Autowired
    TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, tradeService.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade trade) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            tradeService.save(trade);
            model.addAttribute(MODEL_PLURAL_ATTRIBUTE, tradeService.findAll());
            return DEFAULT_REDIRECTION;
        }

        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Trade trade = tradeService.findById(id);
        model.addAttribute(MODEL_SINGULAR_ATTRIBUTE, trade);

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "trade/update";
        }
        tradeService.update(id, trade);
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, tradeService.findAll());

        return DEFAULT_REDIRECTION;
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {

        tradeService.delete(id);
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, tradeService.findAll());

        return DEFAULT_REDIRECTION;
    }
}
