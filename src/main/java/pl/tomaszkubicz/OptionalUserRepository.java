package pl.tomaszkubicz;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszkubicz.model.user.User;

import java.util.Optional;

@Repository
public interface OptionalUserRepository extends CrudRepository<User, Long>{
    Optional<User> findByUserName (String userName);
}
