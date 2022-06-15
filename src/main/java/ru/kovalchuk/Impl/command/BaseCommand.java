package ru.kovalchuk.Impl.command;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kovalchuk.Command;
import ru.kovalchuk.TaskDao;

public abstract class BaseCommand implements Command {
    @Autowired
    protected TaskDao taskDao;
    protected String commandName;

    @Override
    public boolean canProcess(String commandName) {
        return this.commandName.equals(commandName);
    }
}
