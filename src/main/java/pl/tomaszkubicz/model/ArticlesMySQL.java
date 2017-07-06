package pl.tomaszkubicz.model;

import javax.persistence.*;

@Entity
@Table(name = "article_data")
public class ArticlesMySQL {

    @Id
    @Column(name = "articleId")
    @GeneratedValue
    private Long articleId;

    @Column(name="articleImage")
    private String articleImage; // nazwa img, który umieszczony ma zostać na serwerze lub na zewnętrznym hostingu (w tej sytuacji adres)

    @Column(name="articleName")
    private String articleName;

    @Column(name= "articleContent")
    private String articleContent;

    @Column(name="articleAuthor")
    private double articleAuthor;

    @Column(name="articleLikes")
    private int articleLikes;

    @Column(name="articleDislikes")
    private int articleDislikes;

    public ArticlesMySQL witharticleImage(final String articleImage){
        this.articleImage=articleImage;
        return this;
    }

    public ArticlesMySQL witharticleName(final String articleName){
        this.articleName=articleName;
        return this;
    }

    public ArticlesMySQL witharticleContent(final String articleContent){
        this.articleContent=articleContent;
        return this;
    }

    public ArticlesMySQL witharticleAuthor(final double articleAuthor){
        this.articleAuthor=articleAuthor;
        return this;
    }

    public ArticlesMySQL witharticleLikes(final int articleLikes){
        this.articleLikes=articleLikes;
        return this;
    }

    public ArticlesMySQL witharticleDislikes(final int articleDislikes){
        this.articleDislikes=articleDislikes;
        return this;
    }


    public ArticlesMySQL(Long articleId, String articleImage, String articleName, String articleContent, double articleAuthor, int articleLikes, int articleDislikes) {
        this.articleId = articleId;
        this.articleImage = articleImage;
        this.articleName = articleName;
        this.articleContent = articleContent;
        this.articleAuthor = articleAuthor;
        this.articleLikes = articleLikes;
        this.articleDislikes = articleDislikes;
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

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public double getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(double articleAuthor) {
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

    public ArticlesMySQL(){
    }

}
