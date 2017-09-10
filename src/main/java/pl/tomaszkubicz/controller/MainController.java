package pl.tomaszkubicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
=======
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> a43201de1c9b974a3dd5a03d8f5fb3289473e819
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
=======
import pl.tomaszkubicz.OptionalUserRepository;
import pl.tomaszkubicz.UserRepository;
>>>>>>> a43201de1c9b974a3dd5a03d8f5fb3289473e819
import pl.tomaszkubicz.model.UserInfo;
import pl.tomaszkubicz.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> a43201de1c9b974a3dd5a03d8f5fb3289473e819

@Controller
public class MainController {


//    @Autowired
//    OptionalUserRepository optionalUserRepository;

    @Autowired
    UserInfo userInfo;

    @GetMapping("/")
    public String mainPage(){
        return "/mainPage";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

//    @PostMapping("/login")
//    public String postLogin(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword, Model model){
//        System.out.println("Login: " + userName + ", hasło: " + userPassword);
//
//        if(userInfo.isLogged()){
//            System.out.println("User is trying to log in without logging out");
//            return "mainPage";
//        }

//
//        Optional<User> user = optionalUserRepository.findByUserName(userName);
//        if(encoder.encodePassword(userPassword, null).equals(user.get().getUserPassword())) {
//            userInfo.setLogged(true);
//            userInfo.setUser(user.get());
//            model.addAttribute("info", "Zostałeś zalogowany poprawnie");
//            System.out.println(userInfo.getUser().toString());
//        return "mainPage";
//        }
//        System.out.println("USERNAME: " + userName + ", A USERHASłO: " + userPassword);
//        System.out.println(user.get().getUserName() + ", a teraz hasło: " + user.get().getUserPassword());
//        model.addAttribute("info", "Nie udało Ci się zalogować");
//        return "mainPage";
//    }

    @GetMapping("/failureLogin")
    @ResponseBody
    public String failureLogin(){
        return "Logowanie nie przebiegło pomyślnie";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth!=null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        model.addAttribute("user", new User());
        return "mainPage";
    }

//    @GetMapping("/logout")
//    @ResponseBody
//    public String logout(){
//        userInfo.setLogged(false);
//        return "Wylogowano!";
//    }

    @GetMapping("/403")
    @ResponseBody
    public String accessDenied(){
        return "Nie masz uprawnień do przeglądania tej części strony";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String adminPanel(){
        return "To powinien być panel administratora, ale nie został on jeszcze stworzony";
    }
}
