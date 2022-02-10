package org.exchange.springboot.chatrest.mapper;

import org.exchange.springboot.chatrest.dto.MessageDTOCut;
import org.exchange.springboot.chatrest.dto.RoomDTO;
import org.exchange.springboot.chatrest.dto.RoomMessageDTO;
import org.exchange.springboot.chatrest.entity.Room;

import java.util.Set;
import java.util.stream.Collectors;

public final class RoomMapperDTO {

    public static RoomDTO mapToRoomDTO(Room room) {
        return new RoomDTO(room.getId(), room.getName(), PersonMapperDTO.mapToPersonDTO(room.getCreator()));
    }

    public static Room mapToRoom(RoomDTO roomDTO) {
        return new Room(roomDTO.getId(), roomDTO.getName(), PersonMapperDTO.mapToPerson(roomDTO.getCreator()));
    }

    public static RoomMessageDTO mapToRoomMessageDTO(Room room) {
        Set<MessageDTOCut> messageDTOCutSet = room.getMessages().stream()
                .map(m -> new MessageDTOCut(m.getId(), m.getDescription(), m.getAuthor().getId()))
                .collect(Collectors.toSet());
        return new RoomMessageDTO(room.getId(), room.getName(), messageDTOCutSet);
    }
}
