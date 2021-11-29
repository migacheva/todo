package ru.kovalchuk;

import java.util.List;

public interface Command {
    void execute(List<String> params);
}
