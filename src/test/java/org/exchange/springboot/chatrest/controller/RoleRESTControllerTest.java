
package org.exchange.springboot.chatrest.controller;

import org.exchange.springboot.chatrest.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleRESTControllerTest {

    @Autowired
    RoleRESTController roleRESTController;

    @Test
    void whenGETRolesThenStatusIsOK() throws IOException {
        ResponseEntity<List<Role>> roleResponseEntity = roleRESTController.findAll();
        assertThat(
                roleResponseEntity.getStatusCode(),
                equalTo(HttpStatus.OK));
    }

    @Test
    void whenPOSTRoleThenStatusIsOK() throws IOException {
        Role role = new Role("TEST_ROLE");
        ResponseEntity<Role> roleResponseEntity = roleRESTController.create(role);
        assertThat(
                roleResponseEntity.getStatusCode(),
                equalTo(HttpStatus.OK));
        assertTrue(roleResponseEntity.getBody().getId() != 0);
    }

    @Test
    void whenGETRoleByIDThenOK() throws IOException {
        ResponseEntity<Role> roleResponseEntity = roleRESTController.create(new Role("TEST_ROLE"));
        ResponseEntity<Role> role2ResponseEntity = roleRESTController.findById(roleResponseEntity.getBody().getId());
        assertThat(
                role2ResponseEntity.getStatusCode(),
                equalTo(HttpStatus.OK));
    }

    @Test
    void whenDELETERoleThenStatusIsOK() throws IOException {
        ResponseEntity<Role> roleResponseEntity = roleRESTController.create(new Role("TEST_ROLE"));
        Role role = roleResponseEntity.getBody();
        ResponseEntity<Void> voidResponseEntity = roleRESTController.delete(role.getId());
        assertThat(
                voidResponseEntity.getStatusCode(),
                equalTo(HttpStatus.OK));
    }

    @Test
    void whenPUTRoleThenStatusIsOK() throws IOException {
        ResponseEntity<Role> roleResponseEntity = roleRESTController.create(new Role("TEST_ROLE"));
        Role role = roleResponseEntity.getBody();
        role.setName("NEW_TEST_ROLE");
        ResponseEntity<Void> voidResponseEntity = roleRESTController.update(role);
        assertThat(
                voidResponseEntity.getStatusCode(),
                equalTo(HttpStatus.OK));
        assertEquals(role.getName(), roleRESTController.findById(role.getId()).getBody().getName());
    }

}
