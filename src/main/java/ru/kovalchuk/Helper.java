package ru.kovalchuk;

public class Helper {
    static boolean check_exist_task(String[][] list_task) {
        if (list_task[1][0].equals("")){
            System.out.println("Не найдено ни одной задачи");
            return true;
        }
        return false;
    }

    static void toggle_task(String in_progress, String done, String[][] list_task, Integer id_task) {
        if (id_task > 0 & id_task < list_task.length)
        {
            if (list_task[id_task][1].equals(in_progress)) {
                list_task[id_task][1] = done;
            }
            else {
                list_task[id_task][1] = in_progress;
            }
            System.out.println("Статус задачи изменен: ");
            for (int i = 0; i < list_task[id_task].length; ++i) {
                System.out.print(list_task[id_task][i]);
            }
        }
        else{
            System.out.println("Задачи с таким id нет");
        }
        System.out.println();
    }

    static void print_in_progress_tasks(String in_progress, String[][] list_task) {
        System.out.println("Список невыполненных задач: ");
        for (int i = 1; i < list_task.length; ++i) {
            for(int j = 0; j < list_task[i].length; ++j) {
                if (list_task[i][1].equals(in_progress)) {
                    System.out.print(list_task[i][j]);
                }
            }
            System.out.println();
        }
    }

    static void print_all_tasks(String[][] list_task) {
        System.out.println("Список абсолютно всех задач: ");
        for (int i = 0; i < list_task.length; ++i) {
            for(int j = 0; j < list_task[i].length; ++j) {
                System.out.print(list_task[i][j]);
            }
            System.out.println();
        }
    }

    static int add_value_in_todo(int iter, String[][] list_task, String in_progress, String task_name) {
        if (!(task_name.equals("") | task_name.equals(" ") | task_name.equals("\n")))
            for (int i = iter; i< iter +1; i++) {
                list_task[i][0] = Integer.toString(i);
                list_task[i][1] = in_progress;
                list_task[i][2] = task_name;
            }
        else{
            System.out.println("Вводить пустые строки, пробелы, перенос строки и обижать котяток нельзя.");
        }
        iter++;
        return iter;
    }
}
