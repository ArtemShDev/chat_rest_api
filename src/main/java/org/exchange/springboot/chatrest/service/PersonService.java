package org.exchange.springboot.chatrest.service;

import org.exchange.springboot.chatrest.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();

    Person findById(int id);

    void delete(Person person);

    Person save(Person person);

    List<Person> findAllByRoomsId(Integer roomId);
}
