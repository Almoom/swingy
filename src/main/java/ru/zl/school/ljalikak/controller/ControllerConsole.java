package ru.zl.school.ljalikak.controller;

import ru.zl.school.ljalikak.Person;
import ru.zl.school.ljalikak.model.DAOh2;
import ru.zl.school.ljalikak.view.MyRegistrationConsole;

import java.awt.*;

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
    }

    @Override
    public void findPersonAndStartGame(String login) {
        daoH2.read(login);
    }

    @Override
    public void exit() {

    }

    @Override
    public void closeViews() {

    }
}
