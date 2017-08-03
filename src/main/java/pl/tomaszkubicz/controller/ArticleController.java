package pl.tomaszkubicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.tomaszkubicz.dao.ArticleDAO;
import pl.tomaszkubicz.model.article.ArticleMySQL;
import pl.tomaszkubicz.ArticleRepository;
import pl.tomaszkubicz.model.article.ArticleMySQLForm;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
    @Autowired
    ArticleDAO articleDAO;

    @Autowired
    public ArticleRepository articleRepository;

    @GetMapping("/add") // GetMapping is a shortcut of @RequestMapping(method RequestMethod.GET). Since few months there is no difference (before there was one, the consumes attribute)
    public String formAddArticle(Model model){
        model.addAttribute("article", new ArticleMySQLForm());
    return "article/add";
    }

    @PostMapping("/add")
    public String newArticleSuccess(@ModelAttribute("article") @Valid ArticleMySQLForm newArticle, BindingResult result){
        //return "redirect:/article/success";
        //articleDAO.save(article);
        if(result.hasErrors()){
            return "article/add";
        }
        ArticleMySQL article = new ArticleMySQL(newArticle);
        articleRepository.save(article);

        return "article/success";
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


};
