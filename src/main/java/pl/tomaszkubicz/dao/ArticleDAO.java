package pl.tomaszkubicz.dao;

import pl.tomaszkubicz.model.ArticleMySQL;

import java.util.List;

public interface ArticleDAO {
    void save(ArticleMySQL article);
    List<ArticleMySQL> getAll();
}
