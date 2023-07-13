package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsersPage(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("listOfUsers", list);
        return "all-users";
    }

    @GetMapping(value = "/new")
    public String createNewUserPage(User user, Model model) {
        model.addAttribute("newUser", user);
        return "new-user";
    }

    @PostMapping()
    public String createUserInDb(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/update")
    public String updateUserByIdPage(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("user", userService.getUserById(id));
        return "update-user";
    }

    @PatchMapping("/{id}")
    public String applyUserUpdate(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
