package ru.ZIschool.ljalikak;

import java.sql.SQLException;

public interface CommonDAO {
    void createConnection() throws SQLException, ClassNotFoundException;
    void closeConnection() throws SQLException;

    void create(Person person) throws SQLException;
    void read(Person person) throws SQLException;
    void update(Person person);

}
