package ru.kovalchuk;
import lombok.extern.slf4j.Slf4j;
import ru.kovalchuk.Impl.command.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Slf4j
public class CommandFactory {


    public CommandFactory() {}

    Command getCommand(BufferedReader in) throws ArrayIndexOutOfBoundsException, IOException{
        String fullLine = in.readLine();
        fullLine = fullLine.trim();
        String commandName = Helper.getInstance().getCommand(fullLine);
        List<String> commandVariables;
        commandVariables = Helper.getInstance().getData(fullLine);
        if (commandName.equals("add")) {
            return new AddCommand(commandVariables.get(0));
        } else if (commandName.equals("print")) {
            return new PrintCommand(false);
        } else if (commandName.equals("print all")) {
            return new PrintCommand(true);
        } else if (commandName.equals("toggle")) {
            return new ToggleCommand(Integer.parseInt(commandVariables.get(0)));
        } else if (commandName.equals("search")) {
            return new SearchCommand(commandVariables.get(0));
        } else if (commandName.equals("edit")) {
            return new EditCommand(Integer.parseInt(commandVariables.get(0)), commandVariables.get(1));
        } else if (commandName.equals("delete")) {
            return new DeleteCommand(Integer.parseInt((commandVariables.get(0))));
         } else if (commandName.equals("quit")) {
             System.exit(0);
        } else {
            Helper.getInstance().helper();
        }
        return null;
    }
}
