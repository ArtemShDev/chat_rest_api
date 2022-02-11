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
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class RoleRESTControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenGETRolesThenStatusIsOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/roles/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void whenPOSTRoleThenOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/roles/").content("{\"name\": \"Unique_Json\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is("Unique_Json")))
                .andExpect(jsonPath("$.id", Matchers.any(Integer.class)));
    }

    @Test
    public void whenGETRolesByIDThenOK() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/roles/").content("{\"name\": \"Unique_Json\"}")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String respBody = new String(result.getResponse().getContentAsByteArray());
        Pattern pattern = Pattern.compile("id\":\\d+");
        Matcher matcher = pattern.matcher(respBody);
        if (matcher.find()) {
            respBody = matcher.group();
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/api/roles/" + Integer.parseInt(respBody.substring(4)))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Test
    void whenPUTRoleThenOK() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/roles/").content("{\"name\": \"Unique_Json\"}")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String respBody = new String(result.getResponse().getContentAsByteArray());
        Pattern pattern = Pattern.compile("id\":\\d+");
        Matcher matcher = pattern.matcher(respBody);
        if (matcher.find()) {
            respBody = matcher.group();
            mockMvc.perform(MockMvcRequestBuilders
                    .put("/api/roles/").content("{\"id\": "
                            + Integer.parseInt(respBody.substring(4)) + ", \"name\": \"Unique_Json_Modify\"}")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Test
    void whenDELETERoleThenOK() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/roles/").content("{\"name\": \"Unique_Json_DELETE\"}")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String respBody = new String(result.getResponse().getContentAsByteArray());
        Pattern pattern = Pattern.compile("id\":\\d+");
        Matcher matcher = pattern.matcher(respBody);
        if (matcher.find()) {
            respBody = matcher.group();
            mockMvc.perform(MockMvcRequestBuilders
                    .delete("/api/roles/" + Integer.parseInt(respBody.substring(4)))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }
}
