package org.exchange.springboot.chatrest.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PersonRESTControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenGETPersonsThenStatusIsOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/persons/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void whenPOSTPersonThenOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/persons/").content("{\"login\": \"Admin\", "
                        + "\"password\": \"Admin\", \"role\": {\"id\": 1}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.login", Matchers.is("Admin")))
                .andExpect(jsonPath("$.password", nullValue()))
                .andExpect(jsonPath("$.id", Matchers.any(Integer.class)));
    }

    @Test
    public void whenGETPersonsByIDThenOK() throws Exception {
        String json = "{id=1, login=Admin, email=Admin@gmail.com, password=null, role={id=1, name=TEST_ROLE}}";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/persons/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasToString(json)));
    }

    @Test
    void whenPUTPersonThenOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/persons/").content("{\"id\": 1, \"login\": \"Admin\", "
                        + "\"password\": \"Admin\", \"role\": {\"id\": 1}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void whenDELETEPersonThenOK() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/persons/").content("{\"login\": \"FOR_DELETE\", "
                        + "\"password\": \"FOR_DELETE\", \"role\": {\"id\": 1}}")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String respBody = new String(result.getResponse().getContentAsByteArray());
        Pattern pattern = Pattern.compile("id\":\\d+");
        Matcher matcher = pattern.matcher(respBody);
        matcher.find();
        respBody = matcher.group();
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/persons/" + Integer.parseInt(respBody.substring(4)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}