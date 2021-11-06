package ru.kovalchuk;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
        List<Task> taskList = new ArrayList<>();
        try (Scanner in = new Scanner(System.in)) {
            while(true) {
                String cmd = in.nextLine();
                if (cmd.equals("add")) {
                    Helper.addValueInTodo(line, taskList);
                } else if (cmd.equals("print")) {
                    Helper.printInProgressTasks(taskList);
                } else if (cmd.equals("print all"))  {
                    Helper.printAllTasks(taskList);
                } else if (cmd.equals("toggle")) {
                    Helper.toggleTask(line, taskList);
                }  else if (cmd.equals("search")){
                    Helper.searchTask(line, taskList);
                } else if (cmd.equals("edit")){
                    Helper.editTask(line, taskList);
                } else if (cmd.equals("delete")){
                    Helper.deleteTask(line, taskList);
                }else if (cmd.equals("quit")) {
                    in.close();
                } else {
                    System.out.println("Такой команды нет");
                    Helper.helper();
                }
            }
        }
    }
}