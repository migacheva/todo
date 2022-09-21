package ru.kovalchuk.task.model;

import lombok.Data;

@Data
public class TaskFilter {
    private boolean onlyProcessing;
    private Long userId;
    private String searchString;
}
