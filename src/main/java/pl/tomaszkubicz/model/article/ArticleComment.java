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
    private Long commentId;
    @Column (name = "Anonymous")
    private boolean commentAnonymous;
    @Column (name = "Author")
    private String commentAuthor;
    @Column (name = "Date")
    private Timestamp commentDate;
    @Column (name = "Content")
    @Lob
    private String commentContent;
    @Column (name = "Title")
    private String commentTitle;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commentedby")
    private User commentedby;
    private String anonUsername;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "related_to_article")
    private ArticleMySQL commentedArticle;

    public ArticleComment(Long commentId, boolean commentAnonymous, String commentAuthor, Timestamp commentDate, String commentContent, String commentTitle, User commentedby, String anonUsername) {
        this.commentId = commentId;
        this.commentAnonymous = commentAnonymous;
        this.commentAuthor = commentAuthor;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.commentTitle = commentTitle;
        this.commentedby = commentedby;
        this.anonUsername = anonUsername;
    }

    public ArticleComment(){}

    public ArticleComment(ArticleCommentForm articleCommentForm){
        this.commentAnonymous = articleCommentForm.isCommentAnonymous();
        if(articleCommentForm.isCommentAnonymous()) this.commentAuthor = articleCommentForm.getCommentAuthor();
        else this.commentAuthor = "User";
        this.commentDate = Timestamp.valueOf(LocalDateTime.now());
        this.commentContent = articleCommentForm.getCommentContent();
        this.commentTitle = articleCommentForm.getCommentTitle();
        this.commentedby = articleCommentForm.getCommentUser();
        this.anonUsername = articleCommentForm.getAnonUsername();
    }


    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
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

    public User getCommentedby() {
        return commentedby;
    }

    public void setCommentedby(User commentedby) {
        this.commentedby = commentedby;
    }

    public String getAnonUsername() { return anonUsername; }

    public void setAnonUsername(String anonUsername) { this.anonUsername = anonUsername; }

    public ArticleMySQL getCommentedArticle() { return commentedArticle; }

    public void setCommentedArticle(ArticleMySQL commentedArticle) { this.commentedArticle = commentedArticle; }



    @Override
    public String toString() {
        return "ArticleComment{" +
                "commentId=" + commentId +
                ", commentAnonymous=" + commentAnonymous +
                ", commentAuthor='" + commentAuthor + '\'' +
                ", commentDate=" + commentDate +
                ", commentContent='" + commentContent + '\'' +
                ", commentTitle='" + commentTitle + '\'' +
                //", commentedby=" + commentedby +
                //", anonUsername='" + anonUsername + '\'' +
                '}';
    }


}
