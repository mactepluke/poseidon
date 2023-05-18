package com.nnk.springboot.controller;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
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
public class UserController {

    private static final String DEFAULT_REDIRECTION = "redirect:/user/list";
    private static final String MODEL_PLURAL_ATTRIBUTE = "users";
    private static final String MODEL_SINGULAR_ATTRIBUTE = "user";

    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String home(Model model) {
        log.info("Get request received: show user list");

        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, userService.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User user) {
        log.info("Get request received: add user");

        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {

        log.info("Post request received: validate user");

        if (!result.hasErrors()) {
            if (userService.save(user) != null) {
                log.info("User successfully saved");
            }
            model.addAttribute(MODEL_PLURAL_ATTRIBUTE, userService.findAll());
            return DEFAULT_REDIRECTION;
        }

        log.error("Could not add user");

        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        log.info("Get request received: show user update form");

        User user = userService.findById(id);
        user.setPassword("");
        model.addAttribute(MODEL_SINGULAR_ATTRIBUTE, user);

        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {

        log.info("Post request received: update user");

        if (result.hasErrors()) {
            log.error("Could not update user");

            return "user/update";
        }
        if (userService.update(id, user) != null) {
            log.info("User successfully updated");
        }
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, userService.findAll());

        return DEFAULT_REDIRECTION;
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {

        log.info("Get request received: delete user");

        if (userService.delete(id) != null) {
            log.info("User successfully deleted");
        }
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, userService.findAll());
        return DEFAULT_REDIRECTION;
    }
}
