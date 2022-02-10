package org.exchange.springboot.chatrest.dto;
import org.exchange.springboot.chatrest.entity.Message;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class RoomMessageDTO implements Serializable {

    private int id;
    private String name;
    private Set<MessageDTOCut> messages;

    public RoomMessageDTO() {
    }

    public RoomMessageDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoomMessageDTO(int id, String name, Set<MessageDTOCut> messages) {
        this.id = id;
        this.name = name;
        this.messages = messages;
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

    public Set<MessageDTOCut> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageDTOCut> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomMessageDTO room = (RoomMessageDTO) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
