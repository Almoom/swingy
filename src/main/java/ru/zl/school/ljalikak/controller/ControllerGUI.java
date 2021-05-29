package ru.zl.school.ljalikak.controller;

import ru.zl.school.ljalikak.Person;
import ru.zl.school.ljalikak.Place;
import ru.zl.school.ljalikak.model.DAOh2;
import ru.zl.school.ljalikak.model.Level;
import ru.zl.school.ljalikak.view.MyFrame;
import ru.zl.school.ljalikak.view.Warrior;

import java.awt.*;
import java.io.IOException;

public class ControllerGUI implements IController {
    private static DAOh2 daoH2 = DAOh2.getProvider();
    MyFrame f;
    private Level level;
    private Place[][] env;

    public ControllerGUI() throws IOException {
        f = new MyFrame(this);
    }

    public void setEnv(Place[][] env) {
        this.env = env;
    }

    @Override
    public void tryMovePlayer(Point shift) {
        if (level.isLeaveLevel(shift)) {
            level = new Level(level.getPlayer());
        } else {
            level.tryMovePerson(shift);
        }
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
    public void findPersonAndStartGame(String login) {
        Person person = daoH2.read(login);
        if (person == null) return;
        startGame(person);
    }

    private void startGame(Person person) {
        f.repainForGame(person);
        level = new Level(person);
        refresh();
    }


    public void refresh(){
        if (level != null) {
            level.fillEnvironment(env);
        }
    }


    @Override
    public void exit() {

    }

    @Override
    public void closeViews() {

    }

    public void executeCommand(Actions action) {
        switch (action) {
            case MOVE_UP : tryMovePlayer(Level.UP); break;
            case MOVE_DOWN: tryMovePlayer(Level.DOWN); break;
            case MOVE_LEFT: tryMovePlayer(Level.LEFT); break;
            case MOVE_RIGHT: tryMovePlayer(Level.RIGHT); break;
            case EXIT: exit(); break;
            default:
                break;
        }
    }
}
