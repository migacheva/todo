package ru.kovalchuk.task.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class AddTaskRequest {
    @NotBlank
    @Length(max = 25)
    private String nameTask;

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

}
