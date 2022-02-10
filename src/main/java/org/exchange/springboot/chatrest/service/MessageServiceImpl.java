package org.exchange.springboot.chatrest.service;

import org.exchange.springboot.chatrest.dao.MessageRepository;
import org.exchange.springboot.chatrest.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message findById(int id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Message message) {
        messageRepository.delete(message);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
