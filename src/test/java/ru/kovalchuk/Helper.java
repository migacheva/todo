package ru.kovalchuk;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Helper {

    private static RestTemplate restTemplate = new RestTemplate();

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResponseEntity<String> createTask(String name){
        HttpEntity<String> httpEntity = new HttpEntity<>(String.format("{\"nameTask\": \"%s\"}", name));
//        httpEntity.
//        Добавить авторизацию
        ResponseEntity<String> resultCreate =
                restTemplate.postForEntity("http://localhost:8080/task", httpEntity, String.class);
        return resultCreate;
    }
}
