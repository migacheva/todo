package ru.kovalchuk;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;


public class Helper {
    private static final String inProgress = ". [ ] "; // задача не выполнена
    private static final String done = ". [v] "; // задача выполнена

    static boolean checkExistTask(List<Task> taskList) {
        if (taskList.size() == 0){
            System.out.println("Не найдено ни одной задачи");
            return false;
        }
        return true;
    }

    static void toggleTask(List<Task> taskList, String idTask) {
        if (Helper.checkExistTask(taskList)) {
            try {
                int id = Integer.parseInt(idTask) - 1;
                Task task = taskList.get(id);
                task.setDone(!task.isDone());
                printTaskInfo(id, task);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Задачи с таким id нет");
            } catch (NumberFormatException e){
                System.out.println("Необходимо ввести id задачи цифрами");
            }
        }
    }

    static void printTasks(List<Task> taskList, boolean flag_all) {
        if (Helper.checkExistTask(taskList)) {
            if (!flag_all){
                IntStream.range(0, taskList.size())
                        .filter(i -> !taskList.get(i).isDone())
                        .forEach(i -> printTaskInfo(i, taskList.get(i)));
            } else {
                IntStream.range(0, taskList.size())
                        .forEach(i -> printTaskInfo(i, taskList.get(i)));
            }

        }
    }


    static void addValueInTodo(List<Task> taskList, String taskName) {
        if (taskName.isBlank()) {
            System.out.println("Вводить пустые строки, пробелы, перенос строки и обижать котяток нельзя.");
        } else {
            taskList.add(new Task(taskName));
        }
    }

    public static void deleteTask(List<Task> taskList, String idTask) {
        if (Helper.checkExistTask(taskList)) {
            try {
                taskList.remove(Integer.parseInt(idTask) - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Задачи с таким id нет");
            } catch (NumberFormatException e){
                System.out.println("Необходимо ввести id задачи цифрами");
            }
        }
    }

    public static void editTask(List<Task> taskList, String idTask, String newValue) {
        if (Helper.checkExistTask(taskList)) {
            try {
                int id = Integer.parseInt(idTask) - 1;
                taskList.get(id).setName(newValue);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Задачи с таким id нет");
            } catch (NumberFormatException e){
                System.out.println("Необходимо ввести id задачи");
            }
        }
    }

    public static void searchTask(List<Task> taskList, String searchData) {
        if (Helper.checkExistTask(taskList)) {
            boolean somethingSearched = false;
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getName().contains(searchData)) {
                    printTaskInfo(i, taskList.get(i));
                    somethingSearched = true;
                }
            }
            if (!somethingSearched) {
                System.out.println("Ничего не найдено");
            }
        }
    }

    public static void printTaskInfo(int id, Task task) {
        System.out.println((id + 1) + " " + (task.isDone() ? done : inProgress) + " " + task.getName());
    }

    static void helper() {
        System.out.print("""
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
                System.out.println("Введен некорректный формат сообщения");
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e){
            System.out.println("Не найдены дополнительные аргументы");
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
