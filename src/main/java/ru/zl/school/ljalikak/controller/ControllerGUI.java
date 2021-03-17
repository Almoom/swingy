package ru.zl.school.ljalikak.controller;

import ru.zl.school.ljalikak.Person;
import ru.zl.school.ljalikak.model.DAOh2;
import ru.zl.school.ljalikak.view.MyGameFrame;
import ru.zl.school.ljalikak.view.MyFrame;

import java.awt.*;
import java.io.IOException;

public class ControllerGUI implements IController {
    private static DAOh2 daoH2 = DAOh2.getProvider();
    MyFrame f;

    public ControllerGUI() throws IOException {
        f = new MyFrame(this);
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
        startGame(person);
    }

    @Override
    public void findPersonAndStartGame(String login, String password) {
        Person person = daoH2.read(login, password);
        startGame(person);
    }

    private void startGame(Person person) {
        f.repainForGame(person);
    }

    @Override
    public void exit() {

    }

    @Override
    public void closeViews() {

    }
}
