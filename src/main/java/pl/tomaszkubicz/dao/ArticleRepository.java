package pl.tomaszkubicz.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszkubicz.model.article.ArticleMySQL;
import pl.tomaszkubicz.model.user.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleMySQL, Long>{ // Long - type of Id
    ArticleMySQL findByArticleTitle (String articleTitle);
    ArticleMySQL findByArticleId (Long articleId);
    List<ArticleMySQL> findTopByArticleId(int number);
    Page<ArticleMySQL> findAll(Pageable pageable); //because of reading with for-each
}