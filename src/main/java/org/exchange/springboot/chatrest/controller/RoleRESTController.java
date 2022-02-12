package org.exchange.springboot.chatrest.controller;

import org.exchange.springboot.chatrest.entity.Role;
import org.exchange.springboot.chatrest.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/roles")
public class RoleRESTController {

    private final RoleService roles;

    public RoleRESTController(final RoleService roles) {
        this.roles = roles;
    }

    @GetMapping("/")
    public ResponseEntity<List<Role>> findAll() {
        return new ResponseEntity<>(roles.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable int id) {
        Role role = roles.findById(id);
        if (role == null) {
            throw new NoSuchElementException("Role with ID " + id + " was not found");
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Role> create(@Valid @RequestBody Role role) {
        return new ResponseEntity<>(roles.save(role), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@Valid @RequestBody Role role) {
        roles.save(role);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Role role = new Role();
        role.setId(id);
        roles.delete(role);
        return ResponseEntity.ok().build();
    }
}
