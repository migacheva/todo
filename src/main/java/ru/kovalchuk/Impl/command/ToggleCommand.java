package ru.kovalchuk.Impl.command;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.TaskDao;

@Slf4j
public class ToggleCommand extends BaseCommand{

    private int id;

    public ToggleCommand(TaskDao taskDao, int id){
        super.taskDao = taskDao;

        this.id = id;
    }

    @Override
    public void execute() {
        if (taskDao.getById(id) != null) {
            try {
                taskDao.toggleTask(id);
            } catch (IndexOutOfBoundsException e) {
                log.error("Задачи с таким id нет", e);
            } catch (NumberFormatException e){
                log.error("Необходимо ввести id задачи цифрами", e);
            }
        }
    }
}
