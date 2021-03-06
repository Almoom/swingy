package ru.ZIschool.ljalikak.controller;

import ru.ZIschool.ljalikak.Person;
import ru.ZIschool.ljalikak.services.DAOh2;

import java.awt.*;
import java.sql.SQLException;

public class ControllerFrame implements IController {
    private static DAOh2 daoH2;

    static {
        try {
            daoH2 = new DAOh2();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void tryMovePlayer(Point shift) {

    }

    @Override
    public void printMessage(String message) {

    }

    @Override
    public void createNewPersonAndStartGame(Person person) {
        daoH2.write(person);
    }

    @Override
    public void findPersonAndStartGame(String login, String password) {
        daoH2.read(login, password);
    }

    @Override
    public void exit() {

    }

    @Override
    public void closeViews() {

    }
}
