package pl.tomaszkubicz;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszkubicz.model.article.ArticleMySQL;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleMySQL, Long>{ // Long - type of Id
    ArticleMySQL findByArticleTitle (String articleTitle);

}