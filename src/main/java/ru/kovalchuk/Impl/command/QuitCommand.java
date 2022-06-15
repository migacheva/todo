package ru.kovalchuk.Impl.command;

import org.springframework.stereotype.Component;

@Component
public class QuitCommand extends BaseCommand{
    public QuitCommand() {
        commandName = "quit";
    }

    @Override
    public void execute(Object[] params) {
        System.exit(0);
    }

}
