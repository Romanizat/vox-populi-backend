package romanizat.voxpopuli.service;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import romanizat.voxpopuli.entity.*;

public interface UserService {

	List<User> findAll();

	User save(User user);

	User update(User user);

	User findById(Integer idUser);

	void deleteById(Integer idUser);

	List<Role> findAllRolesById(Integer idUser);

	List<Role> addRolesById(Integer idUser, List<Role> roles);

	List<Role> setRolesById(Integer idUser, List<Role> roles);

	List<Role> deleteRolesById(Integer idUser, List<Role> roles);

}