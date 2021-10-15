import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int iter = 1;
        String in_progress = ". [-] "; // задача не выполнена
        String done = ". [v] "; // задача выполнена
        String [][] list_task= {{"id ", "state ", "name"}, {"", "", ""}, {"", "", ""},{"", "", ""}};
        String help_text = """
                Вам доступны следующие команды:\s
                 all/help - все доступные команды\s
                 add - добавление новой задачи\s
                 print - получение невыполненных списка задач\s
                 print [all] - получение всех задач\s
                 toggle - переключение состояния задачи на противоположное\s
                 quit - завершение работы программы\s
                 bonus - безопасно получить хорошее настроение\s
                """;
        Scanner in = new Scanner(System.in);
        for (; ;) {
            System.out.println("Введите команду: ");
            String cmd = in.nextLine();
            System.out.printf("Вы ввели команду: %s ", cmd);
            if (cmd.equals("all") | cmd.equals("help")) {
                System.out.print(help_text);
                System.out.println("На данном этапе можно добавить только 3 таски");
            }
            if (cmd.equals("add")) {
                System.out.print("Введите название задачи:  ");
                String task_name = in.nextLine();
                iter = helper.add_value_in_todo(iter, list_task, in_progress, task_name);
            }
            if (cmd.equals("print")) {
                if (!helper.check_exist_task(list_task))
                {
                    helper.print_in_progress_tasks(in_progress, list_task);
                }
            }
            if (cmd.equals("print [all]"))  {
                if (!helper.check_exist_task(list_task)) {
                    helper.print_all_tasks(list_task);
                }
            }
            if (cmd.equals("toggle")) {
                if (!helper.check_exist_task(list_task)) {
                    System.out.println("Введите id задачи: ");
                    Integer id_task = in.nextInt();
                    helper.toggle_task(in_progress, done, list_task, id_task);
                }
            }
            if (cmd.equals("bonus")) {
                System.out.print("Минутка добра: \n Ты классная(ый), у тебя все получится! ");
            }
            if (cmd.equals("quit")) {
                System.out.print("До свидания!");
                break;
            }
            if (!(cmd.equals("all") | cmd.equals("help") |
                    cmd.equals("add") | cmd.equals("print") | cmd.equals("print [all]") |
                    cmd.equals("toggle") | cmd.equals("bonus")))
            {
                    System.out.println("Такой команды нет, попробуйте ввести all или help");
                }
        }
        in.close();
    }
}