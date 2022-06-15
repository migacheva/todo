package ru.kovalchuk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private CommandFactory factory;
    public static void main(String[] args) {
//        в SpringApplication передаем класс который надо запускать
        SpringApplication.run(Application.class, args);
    }

//    перегружаем метод ран, чтобы выполнялись наши действия
    @Override
    public void run(String... args) throws Exception {
//   String... - преобразует передаваемые строки в массим, можно передавать только последним аргументом
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                try {
                    String fullLine = in.readLine();
                    fullLine = fullLine.trim();
                    String commandName = Helper.getInstance().getCommand(fullLine);
                    List<Object> commandVariables;
                    commandVariables = Helper.getInstance().getData(fullLine);
//                    инъекция зависимостей
                    factory.getCommand(commandName).execute(commandVariables.toArray());
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