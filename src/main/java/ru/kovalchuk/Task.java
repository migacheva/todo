package ru.kovalchuk;
import lombok.Data;

@Data
public class Task {

    private int id;
    private String name;
    private boolean done;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void toggle() {
        done = !done;
    }
}