package pl.tomaszkubicz.model.article;

import pl.tomaszkubicz.model.user.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comments") //I applied @Table annotation because the CamelCase shouldn't be used in MySQL
public class ArticleComment {

    @Id
    @Column(columnDefinition = "INT(13) UNSIGNED")
    @GeneratedValue(strategy=GenerationType.AUTO)
    int commentId;
    @Column (name = "Anonymous")
    boolean commentAnonymous;
    @Column (name = "Author")
    String commentAuthor;
    @Column (name = "Date")
    Timestamp commentDate;
    @Column (name = "Content")
    String commentContent;
    @Column (name = "Title")
    String commentTitle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    private String anonUsername;

    public ArticleComment(int commentId, boolean commentAnonymous, String commentAuthor, Timestamp commentDate, String commentContent, String commentTitle, User user, String anonUsername) {
        this.commentId = commentId;
        this.commentAnonymous = commentAnonymous;
        this.commentAuthor = commentAuthor;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.commentTitle = commentTitle;
        this.user = user;
        this.anonUsername = anonUsername;
    }

    public ArticleComment(){}

    public ArticleComment(ArticleCommentForm articleCommentForm){
        this.commentAnonymous = articleCommentForm.isCommentAnonymous();
        if(articleCommentForm.commentAnonymous) this.commentAuthor = articleCommentForm.getCommentAuthor();
        else this.commentAuthor = "User";
        this.commentDate = Timestamp.valueOf(LocalDateTime.now());
        this.commentContent = articleCommentForm.getCommentContent();
        this.commentTitle = articleCommentForm.getCommentTitle();
        this.user = articleCommentForm.getCommentUser();
        this.anonUsername = articleCommentForm.getAnonUsername();
    }


    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

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

    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAnonUsername() { return anonUsername; }

    public void setAnonUsername(String anonUsername) { this.anonUsername = anonUsername; }
}
