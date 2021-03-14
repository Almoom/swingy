package ru.zl.school.ljalikak.controller;

import ru.zl.school.ljalikak.Person;
import ru.zl.school.ljalikak.model.DAOh2;
import ru.zl.school.ljalikak.view.MyGameFrame;
import ru.zl.school.ljalikak.view.MyRegistrationConsole;

import java.awt.*;
import java.io.IOException;

public class ControllerConsole implements IController {
    private static DAOh2 daoH2 = DAOh2.getProvider();

    public ControllerConsole() {
        MyRegistrationConsole f = new MyRegistrationConsole(this);
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
        try {
            MyGameFrame f = new MyGameFrame("test", 100, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
