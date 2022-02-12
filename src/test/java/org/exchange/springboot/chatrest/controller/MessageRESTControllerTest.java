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
class MessageRESTControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenGETMessagesThenStatusIsOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/messages/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void whenPOSTMessageThenOK() throws Exception {
        String json = "{\"description\":"
                + " \"Post message #2 from User with id = 1 in Room with id = 1\","
                + " \"room\": {\"id\": 1}, \"author\": {\"id\": 1}}";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/messages/").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", Matchers.any(Integer.class)));
    }

    @Test
    public void whenGETMessageByIDThenOK() throws Exception {
        String json = "{id=1, description=TEST DESC MESSAGE, room={id=1, name=TEST_ROOM,"
                + " creator={id=1, login=Admin, email=Admin@gmail.com, password=null, role={id=1, name=TEST_ROLE}}},"
                + " author={id=1, login=Admin, email=Admin@gmail.com, password=null, role={id=1, name=TEST_ROLE}}}";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/messages/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasToString(json)));
    }

    @Test
    void whenPUTAndGetMessageThenOK() throws Exception {
        String json = "{\"id\": 1, \"description\":"
                + " \"Modify message from User with id = 1 in Room with id = 1\","
                + " \"room\": {\"id\": 1}, \"author\": {\"id\": 1}}";
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/messages/").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/messages/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void whenDELETEMessageThenOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/messages/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}