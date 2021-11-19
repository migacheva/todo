package ru.kovalchuk;

import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.command.EditCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CommandFactory {

    TaskDao taskDao;

    public CommandFactory(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    Command getCommand() {
        // читает из консоли
        // условия по обработки команд введенных пользователем

//        String fullLine = in.readLine();
//        fullLine = fullLine.trim();
//        String commandName = Helper.getCommand(fullLine);
//        List<String> commandVariables;
//        try {
//            commandVariables = Helper.getData(fullLine);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            log.error("Не найдены дополнительные аргументы");
//        }

        return new EditCommand(taskDao, 1, "dsfd");
    }

}
