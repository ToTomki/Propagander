package pl.tomaszkubicz.dao;

import org.springframework.stereotype.Repository;
import pl.tomaszkubicz.model.article.ArticleMySQL;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ArticleDAOImplementation implements ArticleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(ArticleMySQL article){
        entityManager.persist(article);

    }

    @Override
    @Transactional
    public List<ArticleMySQL> getAll() {
        Query query = entityManager.createQuery("Select i From ArticleMySQL i");
        return query.getResultList();
    }
}
