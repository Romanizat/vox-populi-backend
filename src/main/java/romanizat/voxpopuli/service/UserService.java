package romanizat.voxpopuli.service;

import romanizat.voxpopuli.entity.Role;
import romanizat.voxpopuli.entity.User;

import java.util.List;

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

    User toggleRecordStatus(Integer userId);

    User findUserByUsername(String username);

}