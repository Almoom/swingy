package ru.zl.school.ljalikak.model;

import ru.zl.school.ljalikak.Person;

import java.sql.SQLException;

public interface IDAO {
    void createConnection();
    void write(Person person) throws SQLException;
    Person read(String login) throws SQLException;
    void update(Person person);

}
