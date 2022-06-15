package ru.kovalchuk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class CommandFactory {
    @Autowired
    private List<Command> commandList;

    Command getCommand(String commandName) throws ArrayIndexOutOfBoundsException, IOException{

        for (Command command: commandList){
            if (command.canProcess(commandName)){
                return command;
            }
        }
        return null;
    }
}
