package org.exchange.springboot.chatrest.mapper;

import org.exchange.springboot.chatrest.dto.PersonDTO;
import org.exchange.springboot.chatrest.entity.Person;

public final class PersonMapperDTO {

    public static PersonDTO mapToPersonDTO(Person person) {
        if (person == null) {
            return null;
        }
        return new PersonDTO(person.getId(),
                person.getLogin(), person.getEmail(), null, person.getRole());
    }

    public static Person mapToPerson(PersonDTO personDTO) {
        return new Person(personDTO.getId(),
                 personDTO.getLogin(), personDTO.getPassword(), personDTO.getEmail(), personDTO.getRole());
    }
}
