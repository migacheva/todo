package ru.kovalchuk;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        CommandFactory factory = new CommandFactory();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                try {
                    factory.getCommand(in).execute();
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