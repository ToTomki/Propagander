package pl.tomaszkubicz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.tomaszkubicz.dao.ArticleDAO;
import pl.tomaszkubicz.model.article.ArticleMySQL;
import pl.tomaszkubicz.ArticleRepository;
import pl.tomaszkubicz.model.article.ArticleMySQLForm;
import pl.tomaszkubicz.model.user.User;
import pl.tomaszkubicz.service.Storage;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
    @Autowired
    ArticleDAO articleDAO;

    @Autowired
    ArticleRepository articleRepository;


    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @GetMapping("/add")
    // GetMapping is a shortcut of @RequestMapping(method RequestMethod.GET). Since few months there is no difference (before there was one, the consumes attribute)
    public String formAddArticle(Model model) {
        model.addAttribute("article", new ArticleMySQLForm());
        return "article/add";
    }

    @PostMapping("/add")
    //@ResponseBody
    public String newArticleSuccess(@ModelAttribute("article") @Valid ArticleMySQLForm newArticle/*, @RequestParam("image") MultipartFile image*/, BindingResult result) {

        if (result.hasErrors()) {
            return "article/add";
        }
//        if (!image.isEmpty()) {
//            try {

        ArticleMySQL article = new ArticleMySQL(newArticle);
//                UUID uuid = UUID.randomUUID();
//                String imageName = "/images/articleHeaders/header_" + uuid.toString();
//                byte[] bytes = image.getBytes();
//                File fsFile = new File(imageName);
//                    fsFile.mkdirs();
//                fsFile.createNewFile();
//                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fsFile));
//                stream.write(bytes);
//                stream.close();
        //I need to change it in the future. Images should be stored outside the server (on amazon, google or smth like that)

        articleRepository.save(article);
        return "article/success";
        //          } catch (Exception e) {
        //            logger.error("Plik nie mógł zostać umieszczony na serwerze", e);
        //        return "article/add";
        //  }
        //}
        //else{
        //  logger.error("Wysłany plik jest pusty");
        //return "article/add";
        //}
    }

    @GetMapping("{articleFile}")
    public String userData(@PathVariable("articleFile") Long articleFile, Model model) {
        ArticleMySQL article = articleRepository.findByArticleId(articleFile);
        //model.addAttribute("article", article);
        //todo I should change attributes to add one article and using it's fields.
        model.addAttribute("articleDate", article.getArticleDate().toString().substring(0,16));
        model.addAttribute("articleImage", article.getArticleImage());
        model.addAttribute("articleAuthor", article.getArticleAuthor());
        model.addAttribute("articleContent", article.getArticleContent());
        model.addAttribute("articleLikes", article.getArticleLikes());
        model.addAttribute("articleDislikes", article.getArticleDislikes());
        return "article/articleData";
    }



    @PostMapping("/articleList")
    public String articleList(ModelMap modelMap) {
        modelMap.addAttribute("articles", articleDAO.getAll());
        return "article/articleList";
    }


}
