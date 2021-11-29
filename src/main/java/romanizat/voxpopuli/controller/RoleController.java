package romanizat.voxpopuli.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.Role;
import romanizat.voxpopuli.entity.User;
import romanizat.voxpopuli.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    @ApiOperation(value = "", nickname = "getAllRoles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{idRole}")
    @ApiOperation(value = "", nickname = "getRoleById")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer idRole) {
        return ResponseEntity.ok(roleService.findById(idRole));
    }

    @PostMapping
    @ApiOperation(value = "", nickname = "saveRole")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role));
    }

    @PutMapping
    @ApiOperation(value = "", nickname = "updateRole")
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.update(role));
    }

    @DeleteMapping("/{idRole}")
    @ApiOperation(value = "", nickname = "deleteRoleById")
    public void deleteRoleById(@PathVariable Integer idRole) {
        roleService.deleteById(idRole);
    }

    @GetMapping("/{idRole}/users")
    @ApiOperation(value = "", nickname = "getRoleUsers")
    public ResponseEntity<List<User>> getRoleUsers(@PathVariable Integer idRole) {
        return ResponseEntity.ok(roleService.findAllUsersById(idRole));
    }

    @PostMapping("/{idRole}/users")
    @ApiOperation(value = "", nickname = "setRoleUsers")
    public ResponseEntity<List<User>> setRoleUsers(@PathVariable Integer idRole, @RequestBody List<User> users) {
        return ResponseEntity.ok(roleService.setUsersById(idRole, users));
    }

    @PutMapping("/{idRole}/users")
    @ApiOperation(value = "", nickname = "addRoleUsers")
    public ResponseEntity<List<User>> addRoleUsers(@PathVariable Integer idRole, @RequestBody List<User> users) {
        return ResponseEntity.ok(roleService.addUsersById(idRole, users));
    }

    @DeleteMapping("/{idRole}/users")
    @ApiOperation(value = "", nickname = "deleteRoleUsers")
    public ResponseEntity<List<User>> deleteRoleUsers(@PathVariable Integer idRole, @RequestBody List<User> users) {
        return ResponseEntity.ok(roleService.deleteUsersById(idRole, users));
    }

}

