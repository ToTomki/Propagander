package pl.tomaszkubicz;

import org.springframework.data.repository.CrudRepository;
import pl.tomaszkubicz.model.ArticlesMySQL;

public interface ProductRepository extends CrudRepository<ArticlesMySQL, Long>{
    ArticlesMySQL findByProductName (String productName);

}
