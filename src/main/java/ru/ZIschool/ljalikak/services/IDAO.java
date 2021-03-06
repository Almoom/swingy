package ru.ZIschool.ljalikak.services;

import ru.ZIschool.ljalikak.Person;

import java.sql.SQLException;

public interface IDAO {
    void createConnection();
    void write(Person person) throws SQLException;
    Person read(String login, String password) throws SQLException;
    void update(Person person);

}
