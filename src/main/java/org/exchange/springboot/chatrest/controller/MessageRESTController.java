package org.exchange.springboot.chatrest.controller;

import org.exchange.springboot.chatrest.dto.MessageDTO;
import org.exchange.springboot.chatrest.entity.Message;
import org.exchange.springboot.chatrest.mapper.MessageMapperDTO;
import org.exchange.springboot.chatrest.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
public class MessageRESTController {

    private final MessageService messages;

    public MessageRESTController(final MessageService messages) {
        this.messages = messages;
    }

    @GetMapping("/")
    public List<MessageDTO> findAll() {
        List<Message> list = messages.findAll();
        return list.stream().map(MessageMapperDTO::mapToMassageDTO
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MessageDTO findById(@PathVariable int id) {
        return MessageMapperDTO.mapToMassageDTO(messages.findById(id));
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@Valid @RequestBody MessageDTO messageDTO) {
        Message message = MessageMapperDTO.mapToMassage(messageDTO);
        messages.save(message);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<MessageDTO> create(@Valid @RequestBody MessageDTO messageDTO) {
        return new ResponseEntity<>(
                MessageMapperDTO.mapToMassageDTO(messages.save(MessageMapperDTO.mapToMassage(messageDTO))),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Message message = new Message();
        message.setId(id);
        messages.delete(message);
        return ResponseEntity.ok().build();
    }
}
