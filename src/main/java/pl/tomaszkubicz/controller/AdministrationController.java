package pl.tomaszkubicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.tomaszkubicz.dao.UserRepository;
import pl.tomaszkubicz.model.user.User;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdministrationController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/administration/controlPanel")
    public String controlPanel(){
        return "administration/controlPanel";
    }

    @PostMapping("/administration/findUser")
    public String findUser(@RequestParam("searchedUser") String searchedUser, Model model){
        List<User> searchResult = new ArrayList();

        try {
            searchResult.add(userRepository.findByUserId(Long.valueOf(searchedUser)));
        }
        catch (InvalidParameterException e){
            throw e;
        }

        finally {
            searchResult.add(userRepository.findByUsername(searchedUser));
            model.addAttribute("searchResult", searchResult);

            return "administration/findUser";
        }
    }

    @GetMapping("/administration/account")
    public String account(Model model){

        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "administration/account";
    }
}

