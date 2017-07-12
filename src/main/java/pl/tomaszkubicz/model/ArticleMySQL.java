package pl.tomaszkubicz.model;

import javax.persistence.*;

@Entity
@Table(name = "Articles") // if I don't need to change the name or other attribute, there can be just @Entity
public class ArticleMySQL {


    @Id
    @Column(name = "Id", columnDefinition = "INT(10) UNSIGNED")
  //  @GeneratedValue
    private int articleId;

    @Column(name="Image")
    private String articleImage; // name of img placed on the server or on an external hosting (in this case - www address)

    @Column(name="Title")
    private String articleTitle;

    @Column(name= "Content")
    private String articleContent;

    @Column(name="Author")
    private String articleAuthor;

    @Column(name="Likes")
    private int articleLikes;

    @Column(name="Dislikes")
    private int articleDislikes;


    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
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

    public ArticleMySQL(int articleId, String articleImage, String articleTitle, String articleContent, String articleAuthor, int articleLikes, int articleDislikes) {
        this.articleId = articleId;
        this.articleImage = articleImage;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleAuthor = articleAuthor;
        this.articleLikes = articleLikes;
        this.articleDislikes = articleDislikes;
    }



    public ArticleMySQL(){
    }

}
