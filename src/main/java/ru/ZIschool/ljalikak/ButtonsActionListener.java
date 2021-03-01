package ru.ZIschool.ljalikak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonsActionListener implements ActionListener {

    private static ArrayList<Person> persons;

    @Override
    public void actionPerformed(ActionEvent e) {
        persons = new ArrayList<>();
        persons.add(new Person("test", "test", "human"));
        if (e.getActionCommand().equals("new")) {
//            persons.add(new Person(e.getSource("login"), ))
        }

    }
}
