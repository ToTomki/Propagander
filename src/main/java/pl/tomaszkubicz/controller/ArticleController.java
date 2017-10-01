package pl.tomaszkubicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.tomaszkubicz.dao.ArticleCommentRepository;
import pl.tomaszkubicz.dao.UserRepository;
import pl.tomaszkubicz.model.article.ArticleComment;
import pl.tomaszkubicz.model.article.ArticleCommentForm;
import pl.tomaszkubicz.model.article.ArticleMySQL;
import pl.tomaszkubicz.dao.ArticleRepository;
import pl.tomaszkubicz.model.article.ArticleMySQLForm;
import pl.tomaszkubicz.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    ArticleCommentRepository commentRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/add")
    // GetMapping is a shortcut of @RequestMapping(method RequestMethod.GET). Since few months there is no difference (before there was one, the consumes attribute)
    public String formAddArticle(Model model) {
        model.addAttribute("article", new ArticleMySQLForm());
        return "article/add";
    }

    @PostMapping("/add")
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

    @GetMapping("/{articleFile}")
    public String userData(@PathVariable("articleFile") String articleFile, Model model) {
        ArticleMySQL article = articleRepository.findByArticleId(Long.valueOf(articleFile));
        model.addAttribute("article", article);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("actualUser", authentication.getName());
        System.out.println("Imię usera = " + authentication.getName());
        model.addAttribute("newCommentForm", new ArticleCommentForm());
        return "article/articleFile";
    }

    @PostMapping("/addComment")
    @ResponseBody
    public String addComment(@ModelAttribute("newComment") ArticleCommentForm newComment, @ModelAttribute("actualUser") String anonUsername){

        ArticleComment articleComment = new ArticleComment(newComment);
        //ArrayList<ArticleComment> commentList = new ArrayList<ArticleComment>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authName = authentication.getName();
        if(authName != null){
        User user = userRepository.findByUsername(authName);
        articleComment.setCommentedby(user);
        user.getUserComments().add(articleComment);
        user.setUserLastComment(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);//Repository.save() is a dual purposed method for Insert as well as Update
        }
        else articleComment.setAnonUsername(anonUsername);
        commentRepository.save(articleComment);

        return "Komentarz dodany";
    }


    @GetMapping("/comment/{comment}")
    @ResponseBody
    public String showComment(@PathVariable("comment") Long commentId){
        ArticleComment comment = commentRepository.findByCommentId(commentId);

        return comment.toString();
    }

    @GetMapping("/articleList")
    public String articleList(@ModelAttribute Model model, Pageable pageable){



        //Pageable pageable = new PageRequest(0, 5, Sort.Direction.DESC, "articleId");
        // Page<ArticleMySQL> articleList = articleRepository.findAll(pageable);
//        model.addAttribute("listSize", page.getTotalPages());
//        model.addAttribute("elements", "There are found: " + articleList.getTotalElements() + " articles");
        return "article/articleList";
    }



}
