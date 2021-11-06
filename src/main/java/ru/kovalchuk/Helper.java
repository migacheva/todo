package ru.kovalchuk;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


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

    static void toggleTask(BufferedReader line, List<Task> taskList) throws IOException {
        if (Helper.checkExistTask(taskList)) {
            // System.out.println("Введите id задачи: ");
            try {
                int idTask = Integer.parseInt(line.readLine()) - 1;
                Task task = taskList.get(idTask);
                task.setDone(!task.isDone());
                printTaskInfo(idTask, task);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Задачи с таким id нет");
            } catch (NumberFormatException e){
                System.out.println("Необходимо ввести id задачи цифрами");
            }
        }
    }

    static void printInProgressTasks(List<Task> taskList) {
        if (Helper.checkExistTask(taskList)) {
            // System.out.println("Список невыполненных задач: ");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (!task.isDone()) {
                    printTaskInfo(i, task);
                }
            }
        }
    }

    static void printAllTasks(List<Task> taskList) {
        if (Helper.checkExistTask(taskList))
        {
            // System.out.println("Список абсолютно всех задач: ");
            for (int i = 0; i < taskList.size(); i++) {
                printTaskInfo(i, taskList.get(i));
            }
        }
    }

    static void addValueInTodo(BufferedReader line, List<Task> taskListListtask) throws IOException {
//        System.out.print("Введите название задачи:  ");
        String taskName = line.readLine();
        if (taskName.isBlank()) {
            System.out.println("Вводить пустые строки, пробелы, перенос строки и обижать котяток нельзя.");
        } else {
            taskListListtask.add(new Task(taskName));
        }
    }

    public static void deleteTask(BufferedReader line, List<Task> taskList) throws IOException {
        if (Helper.checkExistTask(taskList)) {
            // System.out.println("Введите id задачи: ");
            try {
                int idTask = Integer.parseInt(line.readLine()) - 1;
                taskList.remove(idTask);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Задачи с таким id нет");
            } catch (NumberFormatException e){
                System.out.println("Необходимо ввести id задачи цифрами");
            }
        }
    }

    public static void editTask(BufferedReader line, List<Task> taskList) throws IOException {
        if (Helper.checkExistTask(taskList)) {
            try {
                // System.out.println("Введите id задачи: ");
                int idTask = Integer.parseInt(line.readLine()) - 1;
                // System.out.println("Введите новое название задачи: ");
                String newValue = line.readLine();
                taskList.get(idTask).setName(newValue);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Задачи с таким id нет");
            } catch (NumberFormatException e){
                System.out.println("Необходимо ввести id задачи");
            }
        }
    }

    public static void searchTask(BufferedReader line, List<Task> taskList) throws IOException {
        if (Helper.checkExistTask(taskList)) {
            // System.out.println("Текст для поиска: ");
            String search = line.readLine();
            boolean somethingSearched = false;
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getName().contains(search)) {
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
}
