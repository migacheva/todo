package ru.kovalchuk.common.Exception;

public class NoEntityException extends RuntimeException{

    public NoEntityException(Long id) {
        super("Нет такого ID: " + id);
    }
}
