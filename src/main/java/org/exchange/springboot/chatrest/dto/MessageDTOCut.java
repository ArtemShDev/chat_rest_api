package org.exchange.springboot.chatrest.dto;

import java.io.Serializable;
import java.util.Objects;

public class MessageDTOCut implements Serializable {

    private int id;
    private String description;
    private int authorId;

    public MessageDTOCut(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public MessageDTOCut(int id, String description, int authorId) {
        this.id = id;
        this.description = description;
        this.authorId = authorId;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageDTOCut message = (MessageDTOCut) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
