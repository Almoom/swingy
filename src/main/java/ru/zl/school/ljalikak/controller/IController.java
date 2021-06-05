package ru.zl.school.ljalikak.controller;

import ru.zl.school.ljalikak.model.Person;

import java.awt.*;

public interface IController {
    public void tryMovePlayer(Point shift);
    public boolean createNewPersonAndStartGame(Person person);
    public boolean findPersonAndStartGame(String login);
    public void exit();

}
