package ru.zl.school.ljalikak.model;

import java.sql.SQLException;

public interface IDAO {
    void createConnection();
    boolean write(Person person, String mode) throws SQLException;
    Person read(String login, String mode) throws SQLException;
    void update(Person person);

}
