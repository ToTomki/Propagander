package pl.tomaszkubicz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.tomaszkubicz.dao.UserRepository;
import pl.tomaszkubicz.model.user.User;
import pl.tomaszkubicz.model.user.UserForm;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static pl.tomaszkubicz.model.user.enums.UserRole.CHIEF;
import static pl.tomaszkubicz.model.user.enums.UserRole.REDACTOR;

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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (tempUser != null) return "Pseudonim jest zajęty";

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "user/successUser";
    }

    @GetMapping("/editors")
    public String editors(Model model){
        try{

        List<User> editorList = userRepository.findByUserRole(REDACTOR);
        editorList.addAll(userRepository.findByUserRole(CHIEF));
        for (int i = editorList.size(); i > 0; i--){
            if (editorList.get(i-1).getUserDescription() == null){
                editorList.get(i-1).setUserDescription("There is no description of this editor yet. Maybe we should change it?");
                userRepository.save(editorList.get(i-1)); // to not check this in the future again
            }
        }
        model.addAttribute("editorList", editorList);
            for (User editor : editorList) {
                System.out.println(editor.toString());
                System.out.println("----------------");
            }
        }

        catch(IllegalArgumentException e) {
            System.out.println("Probably there was something wrong with list of editors");
            throw e;
        }
        return "/user/editors";
    }


}
