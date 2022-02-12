package org.exchange.springboot.chatrest.controller;

import org.exchange.springboot.chatrest.dto.PersonDTO;
import org.exchange.springboot.chatrest.dto.RoomDTO;
import org.exchange.springboot.chatrest.dto.RoomMessageDTO;
import org.exchange.springboot.chatrest.dto.RoomPersonDTO;
import org.exchange.springboot.chatrest.entity.Room;
import org.exchange.springboot.chatrest.mapper.PersonMapperDTO;
import org.exchange.springboot.chatrest.mapper.RoomMapperDTO;
import org.exchange.springboot.chatrest.service.PersonService;
import org.exchange.springboot.chatrest.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
public class RoomRESTController {

    private final RoomService rooms;
    private final PersonService persons;

    public RoomRESTController(RoomService rooms, PersonService persons) {
        this.rooms = rooms;
        this.persons = persons;
    }

    @GetMapping("/")
    public ResponseEntity<List<RoomDTO>> findAll() {
        List<Room> list = rooms.findAll();
        return new ResponseEntity<>(list.stream().map(RoomMapperDTO::mapToRoomDTO
        ).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable int id) {
        return new ResponseEntity<>(RoomMapperDTO.mapToRoomDTO(rooms.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/messages")
    public ResponseEntity<RoomMessageDTO> findByIdGetMessages(@PathVariable int id) {
        return new ResponseEntity<>(RoomMapperDTO.mapToRoomMessageDTO(rooms.findById(id)), HttpStatus.OK);
    }

    /*
    @GetMapping("/{id}/users")
    public ResponseEntity<List<PersonDTO>> findByIdGetUsers(@PathVariable int id) {
        return new ResponseEntity<>(persons.findAllByRoomsId(id).stream().map(PersonMapperDTO::mapToPersonDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
     */
    @GetMapping("/{id}/users")
    public ResponseEntity<RoomPersonDTO> findByIdGetUsers(@PathVariable int id) {
        return new ResponseEntity<>(RoomMapperDTO.mapToRoomPersonDTO(rooms.findById(id)), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@Valid @RequestBody RoomDTO roomDTO) {
        rooms.save(RoomMapperDTO.mapToRoom(roomDTO));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<RoomDTO> create(@Valid @RequestBody RoomDTO roomDTO) {
        return new ResponseEntity<>(
                RoomMapperDTO.mapToRoomDTO(rooms.save(RoomMapperDTO.mapToRoom(roomDTO))),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Room room = new Room();
        room.setId(id);
        rooms.delete(room);
        return ResponseEntity.ok().build();
    }
}
