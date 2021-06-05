package ru.zl.school.ljalikak.controller;

import ru.zl.school.ljalikak.model.Person;
import ru.zl.school.ljalikak.model.Place;
import ru.zl.school.ljalikak.model.DAOh2;
import ru.zl.school.ljalikak.model.Level;
import ru.zl.school.ljalikak.view.MyFrame;

import java.awt.*;
import java.io.IOException;

public class ControllerGUI implements IController {
    private static DAOh2 daoH2 = DAOh2.getProvider();
    MyFrame f;
    private Level level;
    private Place[][] env;
    private Person person;

    public ControllerGUI() throws IOException {
        f = new MyFrame(this);
    }

    public void setEnv(Place[][] env) {
        this.env = env;
    }

    @Override
    public void tryMovePlayer(Point shift) {
        if (level.isLeaveLevel(shift)) {
            person = level.getPlayer();
            daoH2.update(person);
            level = new Level(level.getPlayer());
        } else {
            level.tryMovePerson(shift);
        }
    }

    @Override
    public boolean createNewPersonAndStartGame(Person person) {
        this.person = person;
        if (daoH2.write(person, "gui")) startGame();
        return true;
    }

    @Override
    public boolean findPersonAndStartGame(String login) {
        person = daoH2.read(login, "gui");
        if (person == null) return false;
        startGame();
        return true;
    }

    private void startGame() {
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
        daoH2.closeDB();
        System.exit(0);
    }

    public void executeCommand(Actions action) {
        switch (action) {
            case MOVE_UP : tryMovePlayer(Level.UP); break;
            case MOVE_DOWN: tryMovePlayer(Level.DOWN); break;
            case MOVE_LEFT: tryMovePlayer(Level.LEFT); break;
            case MOVE_RIGHT: tryMovePlayer(Level.RIGHT); break;
            default:
                break;
        }
    }

    public Person getPerson() {
        return person;
    }
}
