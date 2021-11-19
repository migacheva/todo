package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.TaskDao;

@Slf4j
public class DeleteCommand extends BaseCommand {

    private int id;

    public DeleteCommand(TaskDao taskDao, int id) {
        super.taskDao = taskDao;

        this.id = id;
    }

    @Override
    public void execute() {
        if (taskDao.getById(id) != null) {
            try {
                taskDao.deleteTask(id);
            } catch (IndexOutOfBoundsException e) {
                log.error("Задачи с таким id нет");
            } catch (NumberFormatException e){
                log.error("Необходимо ввести id задачи цифрами");
            }
        }
    }
}
