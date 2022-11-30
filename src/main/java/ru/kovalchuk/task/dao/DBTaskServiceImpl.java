package ru.kovalchuk.task.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.kovalchuk.common.Exception.NoEntityException;
import ru.kovalchuk.task.model.QTask;
import ru.kovalchuk.task.model.Task;
import ru.kovalchuk.task.model.TaskFilter;
import ru.kovalchuk.user.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DBTaskServiceImpl implements TaskService {

    public final TaskRepository taskRepository;

    public DBTaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }


    @Override
    public Task getById(Long id, User user) {
        QTask qTask = QTask.task;
        BooleanExpression taskQuery = qTask.id.eq(id);
        taskQuery = taskQuery.and(qTask.user().id.eq(user.getId()));
        return taskRepository.findOne(taskQuery).orElseThrow(() -> new NoEntityException(id));
    }

    @Override
    public List<Task> getTasks(TaskFilter filter) {
        QTask qTask = QTask.task;
        BooleanExpression taskQuery = qTask.user().id.eq(filter.getUserId());
        if (filter.isOnlyProcessing()){
            taskQuery = taskQuery.and(qTask.done.eq(false));
        }
        if (filter.getSearchString() != null){
            taskQuery = taskQuery.and(qTask.name.like(String.format("%%%s%%", filter.getSearchString())));
        }
        List<Task> result = new ArrayList<>();
//        taskRepository.findAll(taskQuery).forEach(result::add);
        taskRepository.findAll(taskQuery).forEach(elem -> result.add(elem));
        return result;
    }

    @Override
    public Long addTask(String taskName, User user) {
        Task newTask = new Task();
        newTask.setName(taskName);
        newTask.setUser(user);
        newTask = taskRepository.save(newTask);
        return newTask.getId();
    }

    @Override
    public void deleteTask(Long id, User user) {
        taskRepository.deleteById(id);
    }

    @Override
    public void editTask(Long id, String name, User user) {
        Task editTask = getById(id, user);
        editTask.setName(name);
        taskRepository.save(editTask);
    }

    @Override
    public void toggleTask(Long id, User user) {
        Task toggleTask = getById(id, user);
        toggleTask.toggle();
        taskRepository.save(toggleTask);
    }
}
