package org.exchange.springboot.chatrest.dao;

import org.exchange.springboot.chatrest.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    /*
    @Query("select distinct person from Person person "
            + "join fetch person.messages msg where msg.room.id = ?1")
    List<Person> findAllByRoomsId(Integer roomId);
     */
}
