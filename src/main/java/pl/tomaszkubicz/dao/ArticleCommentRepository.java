package pl.tomaszkubicz.dao;

import org.springframework.data.repository.CrudRepository;
import pl.tomaszkubicz.model.article.ArticleComment;

public interface ArticleCommentRepository extends CrudRepository<ArticleComment, Long> {
    ArticleComment findByCommentId(Long commentId);


}
