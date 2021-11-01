package ru.kovalchuk;
import lombok.Data;

@Data
public class Task {

    private boolean done;
    private String name;

    public Task(String name) {
        this.name = name;
    }
}