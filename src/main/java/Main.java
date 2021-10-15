import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int iter = 1;
        String in_progress = ". [-] "; // задача не выполнена
        String done = ". [v] "; // задача выполнена
        String [][] list_task= {{"id ", "state ", "name"}, {"", "", ""}, {"", "", ""},{"", "", ""}};
        String help_text = """
                Вам доступны следующие команды:
                 all/help - все доступные команды
                 add - добавление новой задачи
                 print - получение невыполненных списка задач
                 print [all] - получение всех задач
                 toggle - переключение состояния задачи на противоположное
                 quit - завершение работы программы
                 bonus - безопасно получить хорошее настроение
                """;

        try (Scanner in = new Scanner(System.in)) {
            while(true) {
                System.out.println("Введите команду: ");
                String cmd = in.nextLine();
                System.out.printf("Вы ввели команду: %s ", cmd);
                if (cmd.equals("all") | cmd.equals("help")) {
                    System.out.print(help_text);
                    System.out.println("На данном этапе можно добавить только 3 таски");
                } else if (cmd.equals("add")) {
                    System.out.print("Введите название задачи:  ");
                    String task_name = in.nextLine();
                    iter = Helper.add_value_in_todo(iter, list_task, in_progress, task_name);
                } else if (cmd.equals("print")) {
                    if (Helper.check_exist_task(list_task)) {
                        Helper.print_in_progress_tasks(in_progress, list_task);
                    }
                } else if (cmd.equals("print [all]"))  {
                    if (Helper.check_exist_task(list_task)) {
                        Helper.print_all_tasks(list_task);
                    }
                } else if (cmd.equals("toggle")) {
                    if (Helper.check_exist_task(list_task)) {
                        System.out.println("Введите id задачи: ");
                        Integer id_task = Integer.parseInt(in.nextLine());
                        Helper.toggle_task(in_progress, done, list_task, id_task);
                    }
                } else if (cmd.equals("bonus")) {
                    System.out.print("Минутка добра: \n Ты классная(ый), у тебя все получится! ");
                } else if (cmd.equals("quit")) {
                    System.out.print("До свидания!");
                    break;
                } else {
                    System.out.println("Такой команды нет, попробуйте ввести all или help");
                }
            }
        }
    }
}