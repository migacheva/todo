package ru.kovalchuk;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        List<Task> taskList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String fullLine = in.readLine();
                fullLine = fullLine.trim();
                String commandName = Helper.getCommand(fullLine);
                List<String> commandVariables;
                try {
                    commandVariables = Helper.getData(fullLine);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Не найдены дополнительные аргументы");
                    continue;
                }
                if (commandName.equals("add")) {
                    Helper.addValueInTodo(taskList, commandVariables.get(0));
                } else if (commandName.equals("print")) {
                    Helper.printTasks(taskList, false);
                } else if (commandName.equals("print all")) {
                    Helper.printTasks(taskList, true);
                } else if (commandName.equals("toggle")) {
                    Helper.toggleTask(taskList, commandVariables.get(0));
                } else if (commandName.equals("search")) {
                    Helper.searchTask(taskList, commandVariables.get(0));
                } else if (commandName.equals("edit")) {
                    Helper.editTask(taskList, commandVariables.get(0), commandVariables.get(1));
                } else if (commandName.equals("delete")) {
                    Helper.deleteTask(taskList, commandVariables.get(0));
                } else if (commandName.equals("quit")) {
                    break;
                } else {
                    log.error("Такой команды нет");
                    Helper.helper();
                }
            }
        }
    }
}