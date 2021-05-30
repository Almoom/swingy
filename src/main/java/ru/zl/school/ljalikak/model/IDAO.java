package ru.zl.school.ljalikak.model;

import java.sql.SQLException;

public interface IDAO {
    void createConnection();
    boolean write(Person person) throws SQLException;
    Person read(String login) throws SQLException;
    void update(Person person);

}
