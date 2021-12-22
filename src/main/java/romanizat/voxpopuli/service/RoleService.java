package romanizat.voxpopuli.service;

import romanizat.voxpopuli.entity.Role;
import romanizat.voxpopuli.entity.User;

import java.util.List;

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

    Role findRoleByRole(String role);

}