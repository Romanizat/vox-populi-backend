package romanizat.voxpopuli.controller;

import java.util.List;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.service.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{idRole}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer idRole) {
        return ResponseEntity.ok(roleService.findById(idRole));
    }

    @PostMapping
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role));
    }

    @PutMapping
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.update(role));
    }

    @DeleteMapping("/{idRole}")
    public void deleteRoleById(@PathVariable Integer idRole) {
        roleService.deleteById(idRole);
    }

    @GetMapping("/{idRole}/users")
    public ResponseEntity<List<User>> getRoleUsers(@PathVariable Integer idRole) {
        return ResponseEntity.ok(roleService.findAllUsersById(idRole));
    }

    @PostMapping("/{idRole}/users")
    public ResponseEntity<List<User>> setRoleUsers(@PathVariable Integer idRole, @RequestBody List<User> users) {
        return ResponseEntity.ok(roleService.setUsersById(idRole, users));
    }

    @PutMapping("/{idRole}/users")
    public ResponseEntity<List<User>> addRoleUsers(@PathVariable Integer idRole, @RequestBody List<User> users) {
        return ResponseEntity.ok(roleService.addUsersById(idRole, users));
    }

    @DeleteMapping("/{idRole}/users")
    public ResponseEntity<List<User>> deleteRoleUsers(@PathVariable Integer idRole, @RequestBody List<User> users) {
        return ResponseEntity.ok(roleService.deleteUsersById(idRole, users));
    }

}

