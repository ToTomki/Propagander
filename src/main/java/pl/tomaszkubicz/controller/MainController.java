package pl.tomaszkubicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomaszkubicz.dao.ArticleRepository;
import pl.tomaszkubicz.model.article.ArticleMySQL;
import pl.tomaszkubicz.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

@Autowired
ArticleRepository articleRepository;

    @GetMapping("/")
    public String mainPage(Model model){
//        try {
//            ArrayList<ArticleMySQL> mainPageArticles = articleRepository.findTop15ByArticleId();
//            System.out.println(mainPageArticles.toString());
//            System.out.println("terazdrukujemyzeroijeden");
//            System.out.println(mainPageArticles.get(0).toString() + mainPageArticles.get(1).toString());
//        }
//        catch (Exception e){
//            System.out.println("Znalezienie topów okazało się problemem");
//        }
        List<ArticleMySQL> articleMainSide = articleRepository.findAll(); //todo it is very bad idea, it HAS TO be changed but I had a problem with repository. I will correct it when will have the version of app that will be... well, working. I think the problem can be data-jpa version (findTop doesn't work as I think it should)
        List<ArticleMySQL> articleMainSupport = new ArrayList<ArticleMySQL>();
        ArticleMySQL articleMain = new ArticleMySQL(); //Main article on the main page

        for (int i = articleMainSide.size(); i > 16; i--){
            articleMainSide.remove(i);
            System.out.println("test2, test2, test2, test2, test2, test2, test2, test2, test2, test2, test2, test2, ");
            System.out.println(articleMainSide);
        }

        //try {
            System.out.println("test 3, test 3, test 3, test 3, test 3, test 3, test 3, test 3, test 3, test 3, ");
            if (articleMainSide.size() < 16) {
                System.out.println(articleMainSide.size());
                for (int i = articleMainSide.size() - 1; i >= 0; i--) {
                    if (articleMainSide.get(i) != null) {
                        System.out.println(articleMain.toString() + " - it is articleMain");
                        articleMain = articleMainSide.get(i);
                        System.out.println(articleMain);
                        articleMainSide.remove(i);
                        System.out.println(articleMainSide);
                        break;
                    }
                }
            } else {
                articleMain = articleMainSide.get(articleMainSide.size() - 1);
                System.out.println(articleMain);
                articleMainSide.remove(articleMainSide.size() - 1);
                System.out.println(articleMainSide + "test jakistam 555555, test jakistam 555555, test jakistam 555555, test jakistam 555555, test jakistam 555555, test jakistam 555555, ");
            }
            System.out.println("1111111111111111111111, test przed kolejnym tryem");
            try {
                System.out.println("supporting 1 test, supporting 1 test, supporting 1 test, supporting 1 test, supporting 1 test, supporting 1 test, ");
                if (articleMainSide.size() < 15) {
                    int j = 0;
                    for (int i = articleMainSide.size() - 1; i >= 0 && j < 3; i--) {
                        if (articleMainSide.get(i) != null) {
                            articleMainSupport.add(articleMainSide.get(i));
                            articleMainSide.remove(i);
                            j++;
                            System.out.println("Koleeeeeeeeeeeejny test");
                        }
                    }
                } else {
                    articleMainSupport = articleMainSide.subList(13, 15);
                    for (int i = 3; i > 0; i--) {
                        articleMainSide.remove(articleMainSide.size());
                    }
                }
            }
            catch(Exception e){
                System.out.println("Supporting articles could not be loaded");
            }
//        }
//        catch(Exception e){
//            System.out.println("Main article could not be loaded");
//        }
        System.out.println("test 4, test 4, test 4, test 4, test 4, test 4, test 4, test 4, test 4, ");



//        List<ArticleMySQL> articleMainSupport = new ArrayList<ArticleMySQL>();
//        if (articleMainSide.size() < 15){
//        articleMainSupport = articleMainSide.subList(12, 14);
//        System.out.println("test 4, test 4, test 4, test 4, test 4, test 4, test 4, test 4, test 4, ");
//        }
//        for (int i = 16; i < 12; i--){
//            System.out.println(articleMainSide);
//        articleMainSide.remove(i);
//        System.out.println(articleMainSide);
//        }
//        System.out.println("gotowe");
        model.addAttribute("articleMainSide", articleMainSide);
        model.addAttribute("articleMainSupport", articleMainSupport);
        model.addAttribute("articleMain", articleMain);
        return "mainPage";
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
//        if(encoder.encodePassword(userPassword, null).equals(user.get().getPassword())) {
//            userInfo.setLogged(true);
//            userInfo.setCommentedby(user.get());
//            model.addAttribute("info", "Zostałeś zalogowany poprawnie");
//            System.out.println(userInfo.getCommentedby().toString());
//        return "mainPage";
//        }
//        System.out.println("USERNAME: " + userName + ", A USERHASłO: " + userPassword);
//        System.out.println(user.get().getUsername() + ", a teraz hasło: " + user.get().getPassword());
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

    @GetMapping("/contact")
    @ResponseBody
    public String contact(){
        return "Here will be located contact infos.";
    }

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

