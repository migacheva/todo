package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.TaskDao;

import java.util.List;

@Slf4j
public class DeleteCommand extends BaseCommand {

    public DeleteCommand(TaskDao taskDao) {
        super(taskDao);
    }

    @Override
    public void execute(List<String> params) {
        int id = Integer.parseInt(params.get(0)) - 1;
        try {
            taskDao.deleteTask(id);
        } catch (IndexOutOfBoundsException e) {
            log.error("Задачи с таким id нет");
        }
    }
}
