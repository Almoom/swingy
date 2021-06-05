package ru.zl.school.ljalikak.controller;

import ru.zl.school.ljalikak.model.Level;
import ru.zl.school.ljalikak.model.Person;
import ru.zl.school.ljalikak.model.DAOh2;
import ru.zl.school.ljalikak.model.Place;
import ru.zl.school.ljalikak.view.MyRegistrationConsole;

import java.awt.*;

public class ControllerConsole implements IController {
    private static DAOh2 daoH2 = DAOh2.getProvider();
    private Level level;
    private Place[][] env;
    private Person person;

    public ControllerConsole() {
        MyRegistrationConsole f = new MyRegistrationConsole(this);
    }

    @Override
    public void tryMovePlayer(Point shift) {

    }

    @Override
    public boolean createNewPersonAndStartGame(Person person) {
        this.person = person;
        if (daoH2.write(person, "console")) System.out.println("startGame()");
        else return false;
        return true;
    }

    @Override
    public boolean findPersonAndStartGame(String login) {
        person = daoH2.read(login, "console");
        if (person == null) return false;
        System.out.println("startGame()");
        return true;
    }

    @Override
    public void exit() {

    }

}
