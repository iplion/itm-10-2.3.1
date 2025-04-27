package web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/users/edit")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());

        return "userEdit";
    }

    @GetMapping("/users/edit/{uuid}")
    public String editUser(@PathVariable UUID uuid, Model model) {
        Optional<User> optionalUser = userService.findByUuid(uuid);

        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
        } else {

            return "redirect:/users";
        }

        return "userEdit";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute User user) {
        userService.save(user);

        return "redirect:/users";
    }

    @DeleteMapping("/users/{uuid}")
    public String deleteUser(@PathVariable UUID uuid) {
        userService.deleteByUuid(uuid);

        return "redirect:/users";
    }

    @PutMapping("/users")
    public String updateUser(@ModelAttribute User user) {
        userService.save(user);

        return "redirect:/users";
    }

}
