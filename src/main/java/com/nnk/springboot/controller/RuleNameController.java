package com.nnk.springboot.controller;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {

    private static final String DEFAULT_REDIRECTION = "redirect:/ruleName/list";
    private static final String MODEL_PLURAL_ATTRIBUTE = "ruleNames";
    private static final String MODEL_SINGULAR_ATTRIBUTE = "ruleName";


    @Autowired
    RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        log.info("Get request received: display rulename list");

        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ruleNameService.findAll());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleName(RuleName ruleName) {
        log.info("Get request received: add rulename");

        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

        log.info("Post request received: validate rulename");

        if (!result.hasErrors()) {
            if (ruleNameService.save(ruleName) != null) {
                log.info("RuleName successfully saved");
            }
            model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ruleNameService.findAll());

            return DEFAULT_REDIRECTION;
        }

        log.error("Could not validate rulename");

        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        log.info("Get request received: show rulename update form");

        RuleName ruleName = ruleNameService.findById(id);
        model.addAttribute(MODEL_SINGULAR_ATTRIBUTE, ruleName);

        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {

        log.info("Post request received: update rulename");

        if (result.hasErrors()) {
            log.error("Get request received: could not update rulename");
            return "ruleName/update";
        }
        if (ruleNameService.update(id, ruleName) != null) {
            log.info("RuleName successfully updated");
        }
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ruleNameService.findAll());

        return DEFAULT_REDIRECTION;
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

        log.info("Get request received: delete rulename");

        if (ruleNameService.delete(id) != null) {
            log.info("RuleName successfully deleted");
        }
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, ruleNameService.findAll());

        return DEFAULT_REDIRECTION;
    }
}
