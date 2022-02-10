package org.exchange.springboot.chatrest.controller;

import org.exchange.springboot.chatrest.dto.PersonDTO;
import org.exchange.springboot.chatrest.entity.Person;
import org.exchange.springboot.chatrest.mapper.PersonMapperDTO;
import org.exchange.springboot.chatrest.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
public class PersonRESTController {

    private final PersonService persons;

    public PersonRESTController(final PersonService persons) {
        this.persons = persons;
    }

    @GetMapping("/")
    public List<PersonDTO> findAll() {
        List<Person> list = persons.findAll();
        return list.stream().map(PersonMapperDTO::mapToPersonDTO
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable int id) {
        return PersonMapperDTO.mapToPersonDTO(persons.findById(id));
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@Valid @RequestBody PersonDTO personDTO) {
        persons.save(PersonMapperDTO.mapToPerson(personDTO));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<PersonDTO> create(@Valid @RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(
                PersonMapperDTO.mapToPersonDTO(persons.save(PersonMapperDTO.mapToPerson(personDTO))),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Person person = new Person();
        person.setId(id);
        persons.delete(person);
        return ResponseEntity.ok().build();
    }
}
