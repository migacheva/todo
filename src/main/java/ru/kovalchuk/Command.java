package ru.kovalchuk;

public interface Command {
    boolean canProcess(String command);
    void execute(Object[] params);
}
