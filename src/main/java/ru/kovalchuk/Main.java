package ru.kovalchuk;
import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.TaskDaoImpl;
import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) {
        TaskDao taskDao = new TaskDaoImpl();
        CommandFactory factory = new CommandFactory(taskDao);
        while(true) {
            try {
                factory.getCommand().execute();
            } catch (ArrayIndexOutOfBoundsException e) {
                log.error("Не найдены дополнительные аргументы");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}