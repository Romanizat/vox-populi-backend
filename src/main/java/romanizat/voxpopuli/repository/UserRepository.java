package romanizat.voxpopuli.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import romanizat.voxpopuli.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}