package pl.tomaszkubicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.tomaszkubicz.dao.ArticleDAO;
import pl.tomaszkubicz.model.ArticleMySQL;
import pl.tomaszkubicz.ArticleRepository;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
    @Autowired
    ArticleDAO articleDAO;

    @Autowired
    public ArticleRepository articleRepository;

    @GetMapping("/add") // GetMapping is a shortcut of @RequestMapping(method RequestMethod.GET). Since few months there is no difference (before there was one, the consumes attribute)
    public String formAddArticle(ModelMap modelMap){
        modelMap.addAttribute("article", new ArticleMySQL());
    return "article/add";
    }

    @PostMapping("/success")
    public String newArticleSuccess(@ModelAttribute ArticleMySQL article, ModelMap modelMap){
        modelMap.addAttribute("article", article);
        //return "redirect:/article/success";
        articleDAO.save(article);
        return "article/success";
    }

    @GetMapping("/success")
    public String newArticleFailure(){
        return "article/failure";
    }

    @GetMapping("/articleList")
    public String articleList(ModelMap modelMap){
        modelMap.addAttribute("articles", articleDAO.getAll());
        return "article/articleList";
    }

//
//    @GetMapping("/article/success")
//    public String success(){
//        return "article/success";
//    }


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


}
