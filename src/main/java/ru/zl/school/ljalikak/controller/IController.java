package ru.zl.school.ljalikak.controller;

import ru.zl.school.ljalikak.model.Person;

import java.awt.*;

public interface IController {
    public void tryMovePlayer(Point shift);
    public void createNewPersonAndStartGame(Person person);
    public void findPersonAndStartGame(String login);
    public void exit();

}
