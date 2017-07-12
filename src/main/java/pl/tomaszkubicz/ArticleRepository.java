package pl.tomaszkubicz;

import org.springframework.data.repository.CrudRepository;
import pl.tomaszkubicz.model.ArticleMySQL;

public interface ArticleRepository extends CrudRepository<ArticleMySQL, Long>{
    ArticleMySQL findByArticleTitle (String articleTitle);

}
