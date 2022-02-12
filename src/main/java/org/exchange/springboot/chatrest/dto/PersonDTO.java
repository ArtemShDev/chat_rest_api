package org.exchange.springboot.chatrest.dto;

import org.exchange.springboot.chatrest.entity.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class PersonDTO implements Serializable {

    private int id;
    @NotBlank(message = "login must be > 3 symbols")
    private String login;
    private String email;
    @NotBlank(message = "password must be > 3 symbols")
    private String password;
    @NotNull(message = "The role must be filled in")
    private Role role;

    public PersonDTO() {
    }

    public PersonDTO(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public PersonDTO(int id, String login, String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public PersonDTO(int id, String login, String email, String password, Role role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonDTO person = (PersonDTO) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
