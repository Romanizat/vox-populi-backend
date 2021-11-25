package romanizat.voxpopuli.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.repository.RoleRepository;
import romanizat.voxpopuli.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
	private final RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Integer idRole) {
		return roleRepository.findById(idRole)
				.orElseThrow(() -> new NoSuchElementException("RoleService.notFound"));
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role update(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void deleteById(Integer idRole) {
		roleRepository.deleteById(idRole);
	}

	@Override
	public List<User> findAllUsersById(Integer idRole) {
		return findById(idRole).getUsers();
	}

	@Override
	public List<User> addUsersById(Integer idRole, List<User> users) {
		Role role = findById(idRole);
		role.getUsers().addAll(users);
		return roleRepository.save(role).getUsers();
	}

	@Override
	public List<User> setUsersById(Integer idRole, List<User> users) {
		Role role = findById(idRole);
		role.setUsers(users);
		return roleRepository.save(role).getUsers();
	}

	@Override
	public List<User> deleteUsersById(Integer idRole, List<User> users) {
		Role role = findById(idRole);
		role.getUsers().removeAll(users);
		return roleRepository.save(role).getUsers();
	}


}