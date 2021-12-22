package romanizat.voxpopuli.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import romanizat.voxpopuli.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}