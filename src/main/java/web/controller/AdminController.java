package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String listUsersAdmin(Model model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("user", new User());
        model.addAttribute("roles", userService.getRoles());

        return "usersadmin";
    }

    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/users-update/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @GetMapping("/users-update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/users-delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}