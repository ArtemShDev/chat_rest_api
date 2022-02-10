package org.exchange.springboot.chatrest.service;

import org.exchange.springboot.chatrest.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll();

    Message findById(int id);

    void delete(Message message);

    Message save(Message message);
}
