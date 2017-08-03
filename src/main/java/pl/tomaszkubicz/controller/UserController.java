package pl.tomaszkubicz.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomaszkubicz.UserRepository;
import pl.tomaszkubicz.model.user.User;
import pl.tomaszkubicz.model.user.UserForm;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {

        @Autowired
        public UserRepository userRepository;

        @GetMapping("/addUser")
        public String formAddUser(Model model){
            model.addAttribute("user", new UserForm());
            return "user/addUser";
        }

        @PostMapping("/addUser")
        public String newUserSuccess(@ModelAttribute("user") @Valid UserForm newUser, BindingResult result){
                if(result.hasErrors()){
                    return "user/addUser";
            }
            User user = new User(newUser);
            userRepository.save(user);

            return "user/successUser";
        }

}
