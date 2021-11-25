package romanizat.voxpopuli.controller;

import java.util.List;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.service.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<User> getUserById(@PathVariable Integer idUser) {
        return ResponseEntity.ok(userService.findById(idUser));
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/{idUser}")
    public void deleteUserById(@PathVariable Integer idUser) {
        userService.deleteById(idUser);
    }

    @GetMapping("/{idUser}/roles")
    public ResponseEntity<List<Role>> getUserRoles(@PathVariable Integer idUser) {
        return ResponseEntity.ok(userService.findAllRolesById(idUser));
    }

    @PostMapping("/{idUser}/roles")
    public ResponseEntity<List<Role>> setUserRoles(@PathVariable Integer idUser, @RequestBody List<Role> roles) {
        return ResponseEntity.ok(userService.setRolesById(idUser, roles));
    }

    @PutMapping("/{idUser}/roles")
    public ResponseEntity<List<Role>> addUserRoles(@PathVariable Integer idUser, @RequestBody List<Role> roles) {
        return ResponseEntity.ok(userService.addRolesById(idUser, roles));
    }

    @DeleteMapping("/{idUser}/roles")
    public ResponseEntity<List<Role>> deleteUserRoles(@PathVariable Integer idUser, @RequestBody List<Role> roles) {
        return ResponseEntity.ok(userService.deleteRolesById(idUser, roles));
    }

}

