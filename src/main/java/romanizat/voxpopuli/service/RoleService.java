package romanizat.voxpopuli.service;

import java.util.Collection;
import java.util.List;
import romanizat.voxpopuli.entity.*;

public interface RoleService {

	List<Role> findAll();

	Role save(Role role);

	Role update(Role role);

	Role findById(Integer idRole);

	void deleteById(Integer idRole);

	List<User> findAllUsersById(Integer idRole);

	List<User> addUsersById(Integer idRole, List<User> users);

	List<User> setUsersById(Integer idRole, List<User> users);

	List<User> deleteUsersById(Integer idRole, List<User> users);

}