package ru.zl.school.ljalikak.controller;

import ru.zl.school.ljalikak.model.Person;
import ru.zl.school.ljalikak.model.DAOh2;
import ru.zl.school.ljalikak.view.elems.MyRegistrationConsole;

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

}
