package ru.kovalchuk.task.model;

import lombok.Data;

@Data
public class TaskDTO {
    private Long taskId;
    private String taskName;
    private boolean taskDone;
}
