package ru.kovalchuk.Impl.command;

import ru.kovalchuk.Command;
import ru.kovalchuk.Printer;
import ru.kovalchuk.TaskDao;

public abstract class BaseCommand implements Command {

    protected TaskDao taskDao;
    protected Printer printer;

}
