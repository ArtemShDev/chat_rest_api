package org.exchange.springboot.chatrest.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class MessageDTO implements Serializable {

    private int id;
    @NotBlank(message = "description must be > 0 symbols")
    private String description;
    private RoomDTO room;
    private PersonDTO author;

    public MessageDTO() {
    }

    public MessageDTO(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public MessageDTO(int id, String description, RoomDTO room, PersonDTO author) {
        this.id = id;
        this.description = description;
        this.room = room;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoomDTO(RoomDTO room) {
        this.room = room;
    }

    public PersonDTO getAuthor() {
        return author;
    }

    public void setAuthor(PersonDTO author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageDTO message = (MessageDTO) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
