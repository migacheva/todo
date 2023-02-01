package ru.kovalchuk.task.controller;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
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
import ru.kovalchuk.Helper;
import ru.kovalchuk.task.dao.TaskMapper;
import ru.kovalchuk.task.dao.TaskService;
import ru.kovalchuk.task.model.AddTaskRequest;
import ru.kovalchuk.task.model.EditTaskRequest;
import ru.kovalchuk.task.model.Task;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
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
    public void testAddTask() throws Exception {
        AddTaskRequest testAddTask = new AddTaskRequest();
        testAddTask.setNameTask("Olalaaaa is it a new task");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/task")
                        .content(Helper.asJsonString(testAddTask))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void testAddTaskNotBlank() throws Exception {
        AddTaskRequest testAddTask = new AddTaskRequest();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/task")
                        .content(Helper.asJsonString(testAddTask))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isIAmATeapot());
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void testAddTaskCheckLength() throws Exception {
        AddTaskRequest testAddTask = new AddTaskRequest();
        testAddTask.setNameTask(RandomStringUtils.random(26, true, true));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/task")
                        .content(Helper.asJsonString(testAddTask))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isIAmATeapot());
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void testGetTask() throws Exception {
        given(service.getById(any(), any())).willReturn(new Task(333L, "Olalaaaa is it a new task"));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/task/{id}", 333L))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void testGetTaskBadId() throws Exception {
        given(service.getById(any(), any())).willReturn(new Task(333L, "Olalaaaa is it a new task"));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/task/{id}", "@qqq"))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void testGetAllTask() throws Exception {
        given(service.getTasks(any(), any(), anyBoolean())).willReturn(List.of(new Task(333L, "Olalaaaa is it a new task")));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/task"))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void testDeleteTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/task/{id}", 333L))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void testEditTaskName() throws Exception {
        EditTaskRequest testEditName = new EditTaskRequest();
        testEditName.setNewTaskName("Ola is it a new edit task");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/task/{id}", 333L)
                        .content(Helper.asJsonString(testEditName))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void testEditTaskWithLongName() throws Exception {
        EditTaskRequest testEditName = new EditTaskRequest();
        testEditName.setNewTaskName("Olalaaaa is it a new edit task");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/task/{id}", 333L)
                        .content(Helper.asJsonString(testEditName))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isIAmATeapot());
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void testToggleTask() throws Exception {
        EditTaskRequest testEditName = new EditTaskRequest();
        testEditName.setChangeStatus(false);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/task/{id}", 333L)
                        .content(Helper.asJsonString(testEditName))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}

