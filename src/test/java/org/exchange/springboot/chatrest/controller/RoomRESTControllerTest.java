package org.exchange.springboot.chatrest.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class RoomRESTControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenGETRoomsThenStatusIsOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/rooms/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void whenPOSTRoomThenOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/rooms/").content("{\"name\": \"Room #2\", \"creator\": {\"id\": 1}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", Matchers.is("Room #2")))
                .andExpect(jsonPath("$.id", Matchers.any(Integer.class)));
    }

    @Test
    public void whenGETRoomByIDThenOK() throws Exception {
        String json = "{id=1, name=TEST_ROOM,"
                + " creator={id=1, login=Admin, email=Admin@gmail.com, password=null, role={id=1, name=TEST_ROLE}}}";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/rooms/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasToString(json)));
    }

    @Test
    void whenPUTAndGetRoomThenOK() throws Exception {
        String json = "{\"id\": 1, \"name\": \"Room #2 Modify\", \"creator\": {\"id\": 1}}";
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/rooms/").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/rooms/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void whenClearReferenceAndDELETERoomThenOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/messages/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/rooms/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}