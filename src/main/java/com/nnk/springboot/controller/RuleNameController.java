package com.nnk.springboot.controller;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {

    private static final String DEFAULT_REDIRECTION = "redirect:/ruleName/list";
    private static final String MODEL_PLURAL_ATTRIBUTE = "ruleNames";
    private static final String MODEL_SINGULAR_ATTRIBUTE = "ruleName";


    @Autowired
    RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ruleNameService.findAll());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleName(RuleName ruleName) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            ruleNameService.save(ruleName);
            model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ruleNameService.findAll());
            return DEFAULT_REDIRECTION;
        }

        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        RuleName ruleName = ruleNameService.findById(id);
        model.addAttribute(MODEL_SINGULAR_ATTRIBUTE, ruleName);

        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "ruleName/update";
        }
        ruleNameService.update(id, ruleName);
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ruleNameService.findAll());

        return DEFAULT_REDIRECTION;
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

        ruleNameService.delete(id);
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ruleNameService.findAll());

        return DEFAULT_REDIRECTION;
    }
}
