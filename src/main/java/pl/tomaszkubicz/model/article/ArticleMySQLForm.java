package pl.tomaszkubicz.model.article;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ArticleMySQLForm {

    private String articleImage; // name of img placed on the server or on an external hosting (in this case - www address)
    @NotNull(message = "{NotNull.articleMySQL.articleTitle}") //it can't be attached to ints
    @Size(min = 3, max = 150, message = "{Size.aticleMySQL.articleId}")
    private String articleTitle;
    @NotNull(message = "Należy podać treść artykulu")
    @Size(min = 1) //only for String, Collection, Map, Array and... null
    private String articleContent;
    private String articleAuthor;
    private int articleLikes;
    private int articleDislikes;

    public ArticleMySQLForm(){
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


    //Classic builder (design pattern; because of >3 arguments). We need Id, Title and Content when initialising so I include it in builder's constructor.

    private ArticleMySQLForm(Builder builder) {
        this.articleImage = builder.articleImage;
        this.articleTitle = builder.articleTitle;
        this.articleContent = builder.articleContent;
        this.articleAuthor = builder.articleAuthor;
        this.articleLikes = builder.articleLikes;
        this.articleDislikes = builder.articleDislikes;
    }


    public static class Builder{
        private String articleImage;
        private String articleTitle;
        private String articleContent;
        private String articleAuthor;
        private int articleLikes;
        private int articleDislikes;

        public Builder (String articleTitle, String articleContent){
            this.articleTitle = articleTitle;
            this.articleContent = articleContent;
        }

        public Builder articleImage (String articleImage){
            this.articleImage = articleImage;
            return this;
        }

        public Builder articleAuthor (String articleAuthor){
            this.articleAuthor = articleAuthor;
            return this;
        }

        public Builder articleLikes (int articleDislikes){
            this.articleLikes = articleLikes;
            return this;
        }

        public Builder articleDislikes (int articleDislikes){
            this.articleDislikes = articleDislikes;
            return this;
        }

        public ArticleMySQLForm build(){
            return new ArticleMySQLForm(this);
        }
    }



};
