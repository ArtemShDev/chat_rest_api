package org.exchange.springboot.chatrest.service;

import org.exchange.springboot.chatrest.entity.Room;
import java.util.List;

public interface RoomService {
    List<Room> findAll();

    Room findById(int id);

    void delete(Room room);

    Room save(Room room);
}
