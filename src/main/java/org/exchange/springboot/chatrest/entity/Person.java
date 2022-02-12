package org.exchange.springboot.chatrest.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private String email;
    @ManyToOne(cascade = {CascadeType.DETACH})
    private Role role;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Room> rooms;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Set<Message> messages;

    public Person() {
    }

    public Person(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public Person(int id, String login, String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public Person(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Person(String login, String password, String email, Role role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Person(int id, String login, String password, String email, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
