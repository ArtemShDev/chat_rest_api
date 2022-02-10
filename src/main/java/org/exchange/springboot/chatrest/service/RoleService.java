package org.exchange.springboot.chatrest.service;

import org.exchange.springboot.chatrest.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findById(int id);

    void delete(Role role);

    Role save(Role role);
}
