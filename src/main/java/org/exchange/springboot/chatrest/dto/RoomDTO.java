package org.exchange.springboot.chatrest.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class RoomDTO implements Serializable {

    private int id;
    @NotBlank(message = "name must be > 3 symbols")
    private String name;
    private PersonDTO creator;

    public RoomDTO() {
    }

    public RoomDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoomDTO(int id, String name, PersonDTO creator) {
        this.id = id;
        this.name = name;
        this.creator = creator;
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

    public PersonDTO getCreator() {
        return creator;
    }

    public void setCreator(PersonDTO creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomDTO room = (RoomDTO) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
