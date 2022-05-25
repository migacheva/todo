package ru.kovalchuk;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
public class Helper {

    private static Helper instance;

    public static Helper getInstance(){
        if (instance == null){
            instance = new Helper();
        }
        return instance;
    }

    private Helper(){
        // это приватный (о боже) конструктор
    }

    public void helper() {
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

    public String getCommand(String fullLine) {
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

    public List<String> getData(String fullLine) {
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
