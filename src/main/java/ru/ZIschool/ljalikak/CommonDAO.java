package ru.ZIschool.ljalikak;

import java.sql.SQLException;

public interface CommonDAO {
    void createConnection();
    void write(Person person) throws SQLException;
    Person read(String login, String password) throws SQLException;
    void update(Person person);

}
