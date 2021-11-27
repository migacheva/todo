package ru.kovalchuk;

import java.util.Arrays;
import java.util.Collections;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Helper {

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
            if (command != null) {
                return command;
            } else {
                log.error("Введен некорректный формат сообщения");
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            log.error("Не найдены дополнительные аргументы");
        }
        return "";
    }

    static List<String> getData(String fullLine) {
        String[] splitArray = fullLine.split(" ", 2);
        String command = splitArray[0];
        if (splitArray.length == 1) {
            return Collections.emptyList();
        } else if (splitArray.length == 2) {
            if (command.equals("add")
                    || command.equals("print")
                    || command.equals("toggle")
                    || command.equals("search")
                    || command.equals("delete")) {
                return List.of(splitArray[1]);
            } else if (command.equals("edit")) {
                return Arrays.asList(splitArray[1].split(" ", 2));
            }
        }
        return Collections.emptyList();
    }
}
