package ru.kovalchuk.task.dao;

import org.springframework.stereotype.Component;
import ru.kovalchuk.task.model.Task;
import ru.kovalchuk.task.model.TaskDTO;

@Component
public class TaskMapper {
    public TaskDTO toDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(task.getId());
        taskDTO.setTaskName(task.getName());
        taskDTO.setTaskDone(task.isDone());
        return taskDTO;
    }
}
