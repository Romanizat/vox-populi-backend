package romanizat.voxpopuli.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.Role;
import romanizat.voxpopuli.entity.User;
import romanizat.voxpopuli.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @ApiOperation(value = "", nickname = "getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{idUser}")
    @ApiOperation(value = "", nickname = "getUserById")
    public ResponseEntity<User> getUserById(@PathVariable Integer idUser) {
        return ResponseEntity.ok(userService.findById(idUser));
    }

    @PostMapping
    @ApiOperation(value = "", nickname = "saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping
    @ApiOperation(value = "", nickname = "updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/{idUser}")
    @ApiOperation(value = "", nickname = "deleteUserById")
    public void deleteUserById(@PathVariable Integer idUser) {
        userService.deleteById(idUser);
    }

    @GetMapping("/{idUser}/roles")
    @ApiOperation(value = "", nickname = "getUserRoles")
    public ResponseEntity<List<Role>> getUserRoles(@PathVariable Integer idUser) {
        return ResponseEntity.ok(userService.findAllRolesById(idUser));
    }

    @PostMapping("/{idUser}/roles")
    @ApiOperation(value = "", nickname = "setUserRoles")
    public ResponseEntity<List<Role>> setUserRoles(@PathVariable Integer idUser, @RequestBody List<Role> roles) {
        return ResponseEntity.ok(userService.setRolesById(idUser, roles));
    }

    @PutMapping("/{idUser}/roles")
    @ApiOperation(value = "", nickname = "addUserRoles")
    public ResponseEntity<List<Role>> addUserRoles(@PathVariable Integer idUser, @RequestBody List<Role> roles) {
        return ResponseEntity.ok(userService.addRolesById(idUser, roles));
    }

    @DeleteMapping("/{idUser}/roles")
    @ApiOperation(value = "", nickname = "deleteUserRoles")
    public ResponseEntity<List<Role>> deleteUserRoles(@PathVariable Integer idUser, @RequestBody List<Role> roles) {
        return ResponseEntity.ok(userService.deleteRolesById(idUser, roles));
    }

}

