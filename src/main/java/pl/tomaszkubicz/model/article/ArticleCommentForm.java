package pl.tomaszkubicz.model.article;

import org.springframework.beans.factory.annotation.Autowired;
import pl.tomaszkubicz.dao.UserRepository;
import pl.tomaszkubicz.model.user.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArticleCommentForm {

    @Autowired
    UserRepository userRepository;

    boolean commentAnonymous;
    @Size(min = 3, max = 35, message = "Komentarz powinien zawierać od 3 do 35znaków")
    String commentAuthor;
    @Size(min = 3, max = 300, message = "Komentarz powinien zawierać od 3 do 300znaków")
    @NotNull(message = "Należy podać treść komentarza")
    String commentContent;
    @NotNull(message = "Należy podać tytuł komentarza")
    String commentTitle;
    User commentUser;
    String anonUsername;

    public ArticleCommentForm(boolean commentAnonymous, String commentAuthor, String commentContent, String commentTitle, User commentUser, String anonUsername) {
        this.commentAnonymous = commentAnonymous;
        this.commentAuthor = commentAuthor;
        this.commentContent = commentContent;
        this.commentTitle = commentTitle;
        if(!this.commentAnonymous){
            this.commentUser = null;
        }
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            this.commentUser = userRepository.findByUsername(authentication.getName());}
        else this.commentUser = null;
        this.anonUsername = anonUsername;
    }

    public ArticleCommentForm(){};

    public boolean isCommentAnonymous() {
        return commentAnonymous;
    }

    public void setCommentAnonymous(boolean commentAnonymous) {
        this.commentAnonymous = commentAnonymous;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public User getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(User commentUser) {
        this.commentUser = commentUser;
    }

    public String getAnonUsername() {return anonUsername;    }

    public void setAnonUsername(String anonUsername) { this.anonUsername = anonUsername; }

};
