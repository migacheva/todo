package ru.kovalchuk.task.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovalchuk.task.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByNameContains(String letters);

    @Modifying
    @Query("UPDATE Task t SET t.name = :name WHERE t.id = :id")
    int updateNameByTaskId(
            @Param("name") String name,
            @Param("id") Long id
    );
}
