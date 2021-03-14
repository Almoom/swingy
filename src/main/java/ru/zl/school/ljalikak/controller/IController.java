package ru.zl.school.ljalikak.controller;

import ru.zl.school.ljalikak.Person;

import java.awt.*;

public interface IController {
    public void tryMovePlayer(Point shift);
    public void printMessage(String message);
    public void createNewPersonAndStartGame(Person person);
    public void findPersonAndStartGame(String login, String password);
    public void exit();
    public void closeViews();
//    public void exit(String message);
}
