package ru.kovalchuk.task.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="name", length=50, nullable=false)
    private String name;
    @Column(name="done", nullable=false)
    private boolean done;

    public Task(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Task() {

    }

    public void toggle() {
        done = !done;
    }
}