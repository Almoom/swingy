package ru.zl.school.ljalikak.controller;

import ru.zl.school.ljalikak.model.DAOh2;
import ru.zl.school.ljalikak.model.Level;
import ru.zl.school.ljalikak.model.Person;
import ru.zl.school.ljalikak.view.ConsoleGame;
import ru.zl.school.ljalikak.view.ConsoleReg;

import java.awt.*;

public class ControllerConsole implements IController {
    private static DAOh2 daoH2 = DAOh2.getProvider();
    private Level level;
    private Person person;
    ConsoleGame consoleGame;

    public ControllerConsole() {
        ConsoleReg f = new ConsoleReg(this);
    }

    @Override
    public boolean createNewPersonAndStartGame(Person person) {
        this.person = person;
        try {
            if (daoH2.write(person, "console"))
                startGame();
            else
                return false;
        } catch (RuntimeException e) {
            System.out.println("Некорректный логин: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean findPersonAndStartGame(String login) {
        person = daoH2.read(login, "console");
        if (person == null)
            return false;
        startGame();
        return true;
    }

    @Override
    public void exit() {
        daoH2.closeDB();
        System.exit(0);
    }

    private void startGame() {
        level = new Level(person);
        consoleGame = new ConsoleGame(this, level);
        consoleGame.gameLogic();
    }

    public Person getPerson() {
        return person;
    }


    @Override
    public void tryMovePlayer(Point shift) {
        if (level.isLeaveLevel(shift)) {
            person = level.getPlayer();
            daoH2.update(person);
            startGame();
        } else {
            level.tryMovePerson(shift, "console");
        }
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
}
