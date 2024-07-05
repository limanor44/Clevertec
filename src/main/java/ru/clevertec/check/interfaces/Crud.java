package main.java.ru.clevertec.check.interfaces;

import java.util.ArrayList;

public interface Crud<T> {
    public T getById(int id);
    public ArrayList<T> getAll();
}
