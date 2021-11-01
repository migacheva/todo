package ru.kovalchuk;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
        List<Task> list_task = new ArrayList<>();
        try (Scanner in = new Scanner(System.in)) {
            while(true) {
                String cmd = in.nextLine();
                if (cmd.equals("help")) {
                    Helper.helper();
                } else if (cmd.equals("add")) {
                    Helper.addValueInTodo(line, list_task);
                } else if (cmd.equals("print")) {
                    Helper.printInProgressTasks(list_task);
                } else if (cmd.equals("print all"))  {
                    Helper.printAllTasks(list_task);
                } else if (cmd.equals("toggle")) {
                    Helper.toggleTask(line, list_task);
                }  else if (cmd.equals("search")){
                    Helper.searchTask(line, list_task);
                } else if (cmd.equals("edit")){
                    Helper.editTask(line, list_task);
                } else if (cmd.equals("delete")){
                    Helper.deleteTask(line, list_task);
                }else if (cmd.equals("quit")) {
                    in.close();
                } else {
                    System.out.println("Такой команды нет");
                }
            }
        }
    }
}