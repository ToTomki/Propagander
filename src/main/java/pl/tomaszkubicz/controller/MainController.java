package pl.tomaszkubicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.tomaszkubicz.model.ArticleMySQL;
import pl.tomaszkubicz.ArticleRepository;

@Controller
public class MainController {

    @Autowired
    public ArticleRepository articleRepository;

    @GetMapping("/addArticle") // GetMapping is a shortcut of @RequestMapping(method RequestMethod.GET). Since few months there is no difference (before there was one, the consumes attribute)
    public String formAddArticle(ModelMap modelMap){
        modelMap.addAttribute("article", new ArticleMySQL());
    return "addArticle";
    }

    @PostMapping("/someArticle")
    public String someArticle(@ModelAttribute ArticleMySQL article, ModelMap modelMap){
        modelMap.addAttribute("article", article);
        return "article";

    }

//    @RequestMapping(value="article/addArticle")
//    @ResponseBody //todo usunąć po
//    public String addArticle(){
//        StringBuilder feedback = new StringBuilder();
//
//        ArticleMySQL task = new ArticleMySQL()
//                .witharticleContent("Jakastresc")
//                .witharticleAuthor("Jakistam")
//                .witharticleDislikes(0)
//                .witharticleLikes(0)
//                .witharticleImage("dsfdsfsdf.pl")
//                .witharticleTitle("tytul");
//
//        articleRepository.save(task);
//        for(ArticleMySQL i: articleRepository.findAll()){
//            feedback.append(i).append("<br>");
//        }
//        return feedback.toString();
//    }


  /*  @RequestMapping("/")
        public String mainList(){
        return "index";
        }
  */

}
