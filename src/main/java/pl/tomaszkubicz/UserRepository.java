package pl.tomaszkubicz;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszkubicz.model.user.User;
import pl.tomaszkubicz.model.user.enums.UserRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    User findByUserId (Long userId);
    User findByUserRole (UserRole userRole);
    User findByUserName (String userName);
}
