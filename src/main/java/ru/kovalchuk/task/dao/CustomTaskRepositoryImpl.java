package ru.kovalchuk.task.dao;

import ru.kovalchuk.task.model.Task;
import ru.kovalchuk.task.model.TaskFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomTaskRepositoryImpl implements CustomTaskRepository{

    @PersistenceContext // or even @Autowired
    private EntityManager em;

    @Override
    public List<Task> getTasks(TaskFilter filter) {
        StringBuilder query = new StringBuilder("SELECT task FROM Task task WHERE task.user.id = ?1");
        if (filter.isOnlyProcessing()){
            query.append(" AND done = false");
        }
        if (filter.getSearchString() != null){
            query.append(" AND name LIKE CONCAT('%',:searchStr,'%')");
        }
        Long userId = filter.getUserId();
        TypedQuery<Task> taskQuery = em.createQuery(query.toString(), Task.class);
        taskQuery.setParameter(1, userId);
        if(filter.getSearchString() != null){
            taskQuery.setParameter("searchStr", filter.getSearchString());
        }
        return taskQuery.getResultList();
    }
}
