package ru.kovalchuk.task.model;

import lombok.Data;

//дата - генерирует геттеры и сеттеры при компиляции (Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields. Will also generate setters for all non-final fields, as well as a constructor.)
@Data
public class EditTaskRequest {
    private String newTaskName;

    private boolean changeStatus;
}
