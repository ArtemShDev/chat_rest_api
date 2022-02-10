/*
package org.exchange.springboot.chatrest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class RoleRESTControllerTest {

    @Test
    void whenGETRolesThenStatusIsOK() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequest req = restTemplate.getRequestFactory()
                .createRequest(URI.create("http://localhost:8080/api/roles/"), HttpMethod.GET);
        ClientHttpResponse resp = req.execute();
        assertThat(
                resp.getStatusCode(),
                equalTo(HttpStatus.OK));
    }

    @Test
    void whenPOSTRoleThenStatusIsOK() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String json = "{\"name\": \"Unique_Json\"}";
        ClientHttpRequest req = restTemplate.getRequestFactory()
                .createRequest(URI.create("http://localhost:8080/api/roles/"), HttpMethod.POST);
        req.getBody().write(json.getBytes());
        req.getHeaders().add("content-type", "application/json");
        ClientHttpResponse resp = req.execute();
        assertThat(
                resp.getStatusCode(),
                equalTo(HttpStatus.OK));
    }

    @Test
    void whenPOSTRoleAndDeleteRoleThenStatusIsOK() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequest req = restTemplate.getRequestFactory()
                .createRequest(URI.create("http://localhost:8080/api/roles/"), HttpMethod.POST);
        req.getBody().write("{\"name\": \"Unique_Json\"}".getBytes(StandardCharsets.UTF_8));
        req.getHeaders().add("content-type", "application/json");
        ClientHttpResponse resp = req.execute();
        String str = new String(resp.getBody().readAllBytes());
        Pattern pattern = Pattern.compile("id\":\\d+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            str = matcher.group();
            req = restTemplate.getRequestFactory()
                    .createRequest(URI.create("http://localhost:8080/api/roles/" + Integer.parseInt(str.substring(4))),
                             HttpMethod.DELETE);
            resp = req.execute();
            assertThat(
                    resp.getStatusCode(),
                    equalTo(HttpStatus.OK));
        }
    }

    /*
    @Test
    void whenGETRolesThenPayloadIsOK() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequest req = restTemplate.getRequestFactory()
                .createRequest(URI.create("http://localhost:8080/api/roles/"), HttpMethod.GET);
        req.getHeaders().add("content-type", "application/json");
        ClientHttpResponse resp = req.execute();
        String str = new String(resp.getBody().readAllBytes());
        assertTrue(str.contains("\"id\":1"));
    }
     */
/*
    @Test
    void whenGETRoleByIDThenPayloadIsOK() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequest req = restTemplate.getRequestFactory()
                .createRequest(URI.create("http://localhost:8080/api/roles/1"), HttpMethod.GET);
        ClientHttpResponse resp = req.execute();
        String str = new String(resp.getBody().readAllBytes());
        assertTrue(str.contains("\"id\":1"));
    }
}
*/