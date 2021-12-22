package romanizat.voxpopuli.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.repository.UserRepository;
import romanizat.voxpopuli.service.RoleService;
import romanizat.voxpopuli.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer idUser) {
        return userRepository.findById(idUser)
                .orElseThrow(() -> new NoSuchElementException("UserService.notFound"));
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(this.roleService.findRoleByRole("USER")));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer idUser) {
        userRepository.deleteById(idUser);
    }

    @Override
    public List<Role> findAllRolesById(Integer idUser) {
        return findById(idUser).getRoles();
    }

    @Override
    public List<Role> addRolesById(Integer idUser, List<Role> roles) {
        User user = findById(idUser);
        user.getRoles().addAll(roles);
        return userRepository.save(user).getRoles();
    }

    @Override
    public List<Role> setRolesById(Integer idUser, List<Role> roles) {
        User user = findById(idUser);
        user.setRoles(roles);
        return userRepository.save(user).getRoles();
    }

    @Override
    public List<Role> deleteRolesById(Integer idUser, List<Role> roles) {
        User user = findById(idUser);
        user.getRoles().removeAll(roles);
        return userRepository.save(user).getRoles();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}