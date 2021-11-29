package ru.kovalchuk;
import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.ConsolePrinter;
import ru.kovalchuk.Impl.TaskDaoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        TaskDao taskDao = new TaskDaoImpl();
        Printer printer = new ConsolePrinter();
        CommandFactory factory = new CommandFactory(taskDao, printer);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                try {
                    String fullLine = in.readLine();
                    fullLine = fullLine.trim();
                    String commandName = Helper.getCommand(fullLine);
                    List<String> commandVariables;
                    commandVariables = Helper.getData(fullLine);

                    factory.getCommand(commandName).execute(commandVariables);
                } catch (ArrayIndexOutOfBoundsException e) {
                    log.error("Не найдены дополнительные аргументы");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e){
                    log.error("Введен неверный формат поля");
                } catch (NullPointerException e) {
                    log.error("Такой команды нет");
                }
            }
        }
    }
}