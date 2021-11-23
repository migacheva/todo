package ru.kovalchuk;

import java.util.Collections;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class Helper {


//    static boolean checkExistTask(List<Task> taskList) {
//        if (taskList.size() == 0){
//            log.error("Не найдено ни одной задачи");
//            return false;
//        }
//        return true;
//    }
//

//
//    public static void searchTask(List<Task> taskList, String searchData) {
//        if (Helper.checkExistTask(taskList)) {
//            boolean somethingSearched = false;
//            for (int i = 0; i < taskList.size(); i++) {
//                if (taskList.get(i).getName().contains(searchData)) {
//                    printTaskInfo(i, taskList.get(i));
//                    somethingSearched = true;
//                }
//            }
//            if (!somethingSearched) {
//                log.error("Ничего не найдено");
//            }
//        }
//    }

//    public static void printTaskInfo(int id, Task task) {
//        log.info((id + 1) + " " + (task.isDone() ? done : inProgress) + " " + task.getName());
//    }
//
    static void helper() {
        log.info("""
    Список доступных команд:
     help - все доступные команды
     add - добавление новой задачи
     print - получение невыполненных списка задач
     print all - получение всех задач
     toggle - переключение состояния задачи на противоположное
     edit - изменение задачи
     search - поиск задач
     delete - удаление задачи
     quit - завершение работы программы
    """);
    }

    static String getCommand(String fullLine) {
        String command = fullLine.split(" ")[0];
        try {
            if (fullLine.equals("print")
                    || fullLine.equals("print all")
                    || fullLine.equals("quit")){
                return fullLine;
            } else if (command.equals("add")
                    || command.equals("toggle")
                    || command.equals("search")
                    || command.equals("delete")
                    || command.equals("edit")){
                return command;
            } else {
                log.error("Введен некорректный формат сообщения");
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e){
            log.error("Не найдены дополнительные аргументы");
        }
        return fullLine;
    }

    static List<String> getData(String fullLine) {
        String command = fullLine.split(" ")[0];
        if (fullLine.equals("print")
                || fullLine.equals("print all")
                || fullLine.equals("quit")){
            return Collections.emptyList();
        } else if (command.equals("add")
                || command.equals("toggle")
                || command.equals("search")
                || command.equals("delete")){
            String data = fullLine.split(" ", 2)[1];
            return List.of(data);
        } else if (command.equals("edit")){
            String data1 = fullLine.split(" ", 3)[1];
            String data2 = fullLine.split(" ", 3)[2];
            return List.of(data1, data2);
        }
        return Collections.emptyList();
    }
}
