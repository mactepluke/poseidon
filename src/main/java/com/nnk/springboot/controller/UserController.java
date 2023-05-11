package com.nnk.springboot.controller;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import com.nnk.springboot.util.Check;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private static final String DEFAULT_REDIRECTION = "redirect:/user/list";
    private static final String MODEL_PLURAL_ATTRIBUTE = "users";
    private static final String MODEL_SINGULAR_ATTRIBUTE = "user";

    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, userService.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {

        if (!Check.passwordIsValid(user.getPassword())) {
            result.addError(new ObjectError("password", "Password must be 8-20 chars, 1+ low cap, 1+ high cap and 1+ symbol."));
        }

        if (!result.hasErrors()) {
            userService.save(user);
            model.addAttribute(MODEL_PLURAL_ATTRIBUTE, userService.findAll());
            return DEFAULT_REDIRECTION;
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        user.setPassword("");
        model.addAttribute(MODEL_SINGULAR_ATTRIBUTE, user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {

        if (!Check.passwordIsValid(user.getPassword())) {
            result.addError(new ObjectError("password", "Password must be 8-20 chars, 1+ low cap, 1+ high cap and 1+ symbol."));
        }

        if (result.hasErrors()) {
            return "user/update";
        }
        userService.update(id, user);
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, userService.findAll());

        return DEFAULT_REDIRECTION;
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.delete(id);
        model.addAttribute(MODEL_PLURAL_ATTRIBUTE, userService.findAll());
        return DEFAULT_REDIRECTION;
    }
}
