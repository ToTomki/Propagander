package pl.tomaszkubicz.model.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.tomaszkubicz.auth.UserAuth;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ArticleMySQLForm {


    @Autowired
    UserAuth userAuth;

    private String articleImage; // name of img placed on the server or on an external hosting (in this case - www address)
    @NotNull(message = "{NotNull.articleMySQL.articleTitle}") //it can't be attached to ints
    @Size(min = 3, max = 150, message = "{Size.aticleMySQL.articleId}")
    private String articleTitle;
    @NotNull(message = "Należy podać treść artykulu")
    @Size(min = 1) //only for String, Collection, Map, Array and... null
    @Lob
    private String articleContent;



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

    //Classic builder (design pattern; because of >3 arguments (there was >3 when I created the class. It can be changed in the future)). We need Id, Title and Content when initialising so I include it in builder's constructor.

    private ArticleMySQLForm(Builder builder) {
        this.articleImage = builder.articleImage;
        this.articleTitle = builder.articleTitle;
        this.articleContent = builder.articleContent;
    }


    public static class Builder{
        private String articleImage;
        private String articleTitle;
        private String articleContent;


        public Builder (String articleTitle, String articleContent){
            this.articleTitle = articleTitle;
            this.articleContent = articleContent;
        }

        public Builder articleImage (String articleImage){
            this.articleImage = articleImage;
            return this;
        }


        public ArticleMySQLForm build(){
            return new ArticleMySQLForm(this);
        }
    }



};
