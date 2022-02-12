package org.exchange.springboot.chatrest.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class RoomPersonDTO implements Serializable {

    private int id;
    private String name;
    private Set<PersonDTO> persons;

    public RoomPersonDTO() {
    }

    public RoomPersonDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoomPersonDTO(int id, String name, Set<PersonDTO> persons) {
        this.id = id;
        this.name = name;
        this.persons = persons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(Set<PersonDTO> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomPersonDTO room = (RoomPersonDTO) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
