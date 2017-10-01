package pl.tomaszkubicz.model.article;

        import org.hibernate.validator.constraints.Length;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.context.SecurityContextHolder;
        import pl.tomaszkubicz.auth.UserAuth;

        import javax.persistence.*;
        import java.sql.Timestamp;
        import java.time.LocalDateTime;
        import java.util.List;


@Entity
@Table(name = "Articles") // if I don't need to change the name or enums attribute, there can be just @Entity
public class ArticleMySQL {

    @Column(name = "Id", columnDefinition = "INT(10) UNSIGNED")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) // this strategy is default
    private Long articleId;
    @Column(name="Image") //@Column is optional
    private String articleImage; // name of img placed on the server or on an external hosting (in this case - www address)
    @Column(name="Title")
    private String articleTitle;
    @Lob
    @Column(name= "Content")
    private String articleContent;
    @Column(name="Author")
    private String articleAuthor;
    @Column(name="Likes")
    private int articleLikes;
    @Column(name="Dislikes")
    private int articleDislikes;
    @Column(name="Date")
    private Timestamp articleDate;
    @Lob
    @Column(name="Introduction")
    private String articleIntroduction;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "commentedArticle")
    private List <ArticleComment> commentList;

    public ArticleMySQL() {
    }

    public ArticleMySQL(Long articleId, String articleImage, String articleTitle, String articleContent, String articleAuthor, int articleLikes, int articleDislikes, Timestamp articleDate, List commentList, String articleIntroduction) {
        this.articleId = articleId;
        this.articleImage = articleImage;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleAuthor = articleAuthor;
        this.articleLikes = articleLikes;
        this.articleDislikes = articleDislikes;
        this.articleDate = articleDate;
        this.commentList = commentList;
        this.articleIntroduction = articleIntroduction;
    }

    public ArticleMySQL(ArticleMySQLForm articleMySQLForm){
        this.articleImage = articleMySQLForm.getArticleImage();
        this.articleTitle = articleMySQLForm.getArticleTitle();
        this.articleContent = articleMySQLForm.getArticleContent();
        this.articleLikes = 0;
        this.articleDislikes = 0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        this.articleAuthor = authentication.getName();
        this.articleDate = Timestamp.valueOf(LocalDateTime.now());
        StringBuilder stringBuilder = new StringBuilder();
        if (articleContent.length() >= 100){
            stringBuilder.append(this.articleContent.substring(0,99) + "...");
            this.articleIntroduction = stringBuilder.toString();}
        else this.articleIntroduction = articleContent;
    }


    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public int getArticleLikes() {
        return articleLikes;
    }

    public void setArticleLikes(int articleLikes) {
        this.articleLikes = articleLikes;
    }

    public int getArticleDislikes() {
        return articleDislikes;
    }

    public void setArticleDislikes(int articleDislikes) {
        this.articleDislikes = articleDislikes;
    }

    public Timestamp getArticleDate() {return articleDate;}

    public void setArticleDate(Timestamp articleDate) {this.articleDate = articleDate; }

    public String getArticleIntroduction() { return articleIntroduction; }

    public void setArticleIntroduction(String articleIntroduction) { this.articleIntroduction = articleIntroduction; }

    public List getCommentList() { return commentList; }

    public void setCommentList(List commentList) { this.commentList = commentList; }


};