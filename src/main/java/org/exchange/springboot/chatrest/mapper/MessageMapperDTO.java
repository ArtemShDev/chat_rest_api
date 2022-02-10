package org.exchange.springboot.chatrest.mapper;

import org.exchange.springboot.chatrest.dto.MessageDTO;
import org.exchange.springboot.chatrest.dto.PersonDTO;
import org.exchange.springboot.chatrest.dto.RoomDTO;
import org.exchange.springboot.chatrest.entity.Message;
import org.exchange.springboot.chatrest.entity.Person;
import org.exchange.springboot.chatrest.entity.Room;

public final class MessageMapperDTO {

    public static MessageDTO mapToMassageDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO(message.getId(), message.getDescription());
        PersonDTO personDTO = null;
        if (message.getRoom().getCreator() != null) {
            personDTO = PersonMapperDTO.mapToPersonDTO(message.getRoom().getCreator());
        }
        messageDTO.setRoomDTO(new RoomDTO(message.getRoom().getId(),
                 message.getRoom().getName(), personDTO));
        messageDTO.setAuthor(PersonMapperDTO.mapToPersonDTO(message.getAuthor()));
        return messageDTO;
    }

    public static Message mapToMassage(MessageDTO messageDTO) {
        Message message = new Message(messageDTO.getId(), messageDTO.getDescription());
        Person person = null;
        if (messageDTO.getRoom().getCreator() != null) {
            person = PersonMapperDTO.mapToPerson(messageDTO.getRoom().getCreator());
        }
        message.setRoom(new Room(messageDTO.getRoom().getId(),
                messageDTO.getRoom().getName(), person));
        message.setAuthor(PersonMapperDTO.mapToPerson(messageDTO.getAuthor()));
        return message;
    }

}
