package ru.kovalchuk.task.model;

import lombok.Data;
import ru.kovalchuk.user.model.User;

import javax.persistence.*;

@Data
@Entity
@Table(name="t_task")
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", length=50, nullable=false)
    private String name;
    @Column(name="done", nullable=false)
    private boolean done;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

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