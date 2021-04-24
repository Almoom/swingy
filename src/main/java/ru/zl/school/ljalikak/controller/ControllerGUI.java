package ru.zl.school.ljalikak.controller;

import ru.zl.school.ljalikak.Person;
import ru.zl.school.ljalikak.Place;
import ru.zl.school.ljalikak.model.DAOh2;
import ru.zl.school.ljalikak.model.Level;
import ru.zl.school.ljalikak.view.MyFrame;

import java.awt.*;
import java.io.IOException;

public class ControllerGUI implements IController {
    private static DAOh2 daoH2 = DAOh2.getProvider();
    MyFrame f;
    private Place[][] env;

    public ControllerGUI() throws IOException {
        f = new MyFrame(this);
        env = new Place[11][11];
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

    @Override
    public void fillEnvironment(Person player) {
        int height = env.length;
        int width = env[0].length;
        Point center = player.getPos();

//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                int y = center.y + i - height / 2;
//                int x = center.x + j - width / 2;
//
//                env[i][j] = getPlace(x, y);
//            }
//        }
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
