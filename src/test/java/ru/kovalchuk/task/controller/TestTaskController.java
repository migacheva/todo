package ru.kovalchuk.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.kovalchuk.task.dao.TaskMapper;
import ru.kovalchuk.task.dao.TaskService;
import ru.kovalchuk.task.model.AddTaskRequest;
import ru.kovalchuk.task.model.Task;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TestTaskController {

    @MockBean
    private TaskService service;

    @MockBean
    private TaskMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void testAddTask() throws Exception{
        given(service.getById(any(), any())).willReturn(new Task(333L, "Olalaaaa is it a new task"));
        AddTaskRequest testAddTask = new AddTaskRequest();
        testAddTask.setNameTask("Olalaaaa is it a new task");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/task")
                        .content(asJsonString(testAddTask))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

