package pl.tomaszkubicz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.tomaszkubicz.dao.UserRepository;
import pl.tomaszkubicz.model.user.User;
import pl.tomaszkubicz.model.user.UserForm;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/addUser")
    public String formAddUser(Model model) {
        model.addAttribute("user", new UserForm());
        return "user/addUser";
    }

    @PostMapping("/addUser")
    public String newUserSuccess(@ModelAttribute("user") @Valid UserForm newUser, BindingResult result) {
        if (result.hasErrors()) {
            return "user/addUser";
        }
        User user = new User(newUser);
        User tempUser = userRepository.findByUsername(user.getUsername());
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();

        if (tempUser != null) return "Pseudonim jest zajęty";

        user.setPassword(encoder.encodePassword((user.getPassword()), null));
        userRepository.save(user);
        return "user/successUser";
    }

    @GetMapping("/{userData}")
    public String userData(@PathVariable("userData") Long userData, Model model){

        User user = userRepository.findByUserId(userData);
        model.addAttribute("userData", user.toString());
        return "user/userData";
    }


}
