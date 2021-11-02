package ru.kovalchuk;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Slf4j
public class Helper {
    private static final String in_progress = ". [ ] "; // задача не выполнена
    private static final String done = ". [v] "; // задача выполнена


    static boolean check_exist_task(List<Task> list_task) {
        if (list_task.size() == 0){
            log.error("Не найдено ни одной задачи");
            return false;
        }
        return true;
    }

    static void toggleTask(BufferedReader line, List<Task> list_task) throws IOException {
        if (Helper.check_exist_task(list_task)) {
            log.debug("id задачи: ");
            int id_task = Integer.parseInt(line.readLine()) - 1;
            try {
                Task task = list_task.get(id_task);
                task.setDone(!task.isDone());
                printTaskInfo(id_task, task);
            }
            catch (IndexOutOfBoundsException e) {
                log.error("Задачи с таким id нет", e);
            }
        }
    }

    static void printInProgressTasks(List<Task> list_task) {
        if (Helper.check_exist_task(list_task)) {
            log.debug("Список невыполненных задач: ");
            for (int i = 0; i < list_task.size(); i++) {
                Task task = list_task.get(i);
                if (!task.isDone()) {
                    printTaskInfo(i, task);
                }
            }
        }
    }

    static void printAllTasks(List<Task> list_task) {
        if (Helper.check_exist_task(list_task))
        {
            log.debug("Список абсолютно всех задач: ");
            for (int i = 0; i < list_task.size(); i++) {
                printTaskInfo(i, list_task.get(i));
            }
        }
    }

    static void addValueInTodo(BufferedReader line, List<Task> list_task) throws IOException {
        log.debug("Название задачи:  ");
        String taskName = line.readLine();
        if (taskName.isBlank()) {
            log.error("Вводить пустые строки, пробелы, перенос строки и обижать котяток нельзя.");
        } else {
            list_task.add(new Task(taskName));
        }
    }

    public static void deleteTask(BufferedReader line, List<Task> list_task) throws IOException {
        if (Helper.check_exist_task(list_task)) {
            log.debug("id задачи: ");
            int id_task = Integer.parseInt(line.readLine()) - 1;
            try {
                list_task.remove(id_task);
            } catch (IndexOutOfBoundsException e) {
                log.error("Задачи с таким id нет");
            }
        }
    }

    public static void editTask(BufferedReader line, List<Task> list_task) throws IOException {
        if (Helper.check_exist_task(list_task)) {
            log.debug("id задачи: ");
            int id_task = Integer.parseInt(line.readLine()) - 1;
            log.debug("Новое название задачи: ");
            String new_value = line.readLine();
            try {
                list_task.get(id_task).setName(new_value);
            } catch (IndexOutOfBoundsException e) {
                log.error("Задачи с таким id нет");
            }
        }
    }

    public static void searchTask(BufferedReader line, List<Task> list_task) throws IOException {
        if (Helper.check_exist_task(list_task)) {
            log.debug("Текст для поиска: ");
            String search = line.readLine();

            boolean somethingSearched = false;
            for (int i = 0; i < list_task.size(); i++) {
                if (list_task.get(i).getName().contains(search)) {
                    printTaskInfo(i, list_task.get(i));
                    somethingSearched = true;
                }
            }

            if (!somethingSearched) {
                log.error("Ничего не найдено");
            }
        }
    }

    public static void printTaskInfo(int id, Task task) {
        log.info((id + 1) + " " + (task.isDone() ? done : in_progress) + " " + task.getName());
    }

    static void helper() {
        log.info("""
                    Вам доступны следующие команды:
                     help - все доступные команды
                     add - добавление новой задачи
                     print - получение невыполненных списка задач
                     print all - получение всех задач
                     toggle - переключение состояния задачи на противоположное
                     quit - завершение работы программы
    """);
    }
}
