package ru.ZIschool.ljalikak.controller;

import ru.ZIschool.ljalikak.Person;
import ru.ZIschool.ljalikak.services.DAOh2;
import ru.ZIschool.ljalikak.view.MyGameFrame;
import ru.ZIschool.ljalikak.view.MyRegistrationFrame;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ControllerGUI implements IController {
    private static DAOh2 daoH2 = DAOh2.getProvider();
    MyRegistrationFrame f;

    public ControllerGUI() {
        f = new MyRegistrationFrame(this);
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
            MyGameFrame ff = new MyGameFrame("test", 100, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
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
