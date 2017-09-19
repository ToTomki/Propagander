package pl.tomaszkubicz.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszkubicz.model.article.ArticleMySQL;

import java.util.ArrayList;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleMySQL, Long>{ // Long - type of Id
    ArticleMySQL findByArticleTitle (String articleTitle);
    ArticleMySQL findByArticleId (Long articleId);
    ArrayList<ArticleMySQL> findTopByArticleId(int number);
}