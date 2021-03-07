package ru.ZIschool.ljalikak.view;

import ru.ZIschool.ljalikak.Person;
import ru.ZIschool.ljalikak.Race;
import ru.ZIschool.ljalikak.controller.ControllerFrame;

import javax.swing.*;
import java.awt.*;

public class MyRegistrationFrame extends JFrame {
    private Container c;
    private JLabel title;
    private JLabel login;
    private JTextField tlogin;
    private JLabel password;
    private JTextField tpassword;
    private JLabel race;
    private JRadioButton human;
    private JRadioButton mutant;
    private JRadioButton ghoul;
    private ButtonGroup racep;
    private JButton create;
    private JButton cont;
    private Race type;

    public MyRegistrationFrame(ControllerFrame controllerFrame) {

        setTitle("Registration Form");
        setBounds(300, 90, 490, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(33, 33, 33));
        //#d9af2f - yellow

        title = new MyMainLabel("Pip-boy game");
        c.add(title);
        login = new MyLabel("Login");
        c.add(login);
        tlogin = new MyTextField();
        c.add(tlogin);
        password = new MyLabel("Password");
        c.add(password);
        tpassword = new MyTextField();
        c.add(tpassword);
        race = new MyLabel("Race");
        c.add(race);

        human = new MyRadioButton(Race.HUMAN.toString().toLowerCase());
        human.addActionListener(e -> type = Race.valueOf(e.getActionCommand().toUpperCase()));
        c.add(human);

        mutant = new MyRadioButton(Race.MUTANT.toString().toLowerCase());
        mutant.addActionListener(e -> type = Race.valueOf(e.getActionCommand().toUpperCase()));
        c.add(mutant);

        ghoul = new MyRadioButton(Race.GHOUL.toString().toLowerCase());
        ghoul.addActionListener(e -> type = Race.valueOf(e.getActionCommand().toUpperCase()));
        c.add(ghoul);

        racep = new ButtonGroup();
        racep.add(human);
        racep.add(ghoul);
        racep.add(mutant);

        create = new MyButton("new");
        create.addActionListener(e -> {
            try {
                controllerFrame.createNewPersonAndStartGame(new Person(tlogin.getText(), tpassword.getText(), type));
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Message",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        c.add(create);

        cont = new MyButton("continue");
        cont.addActionListener(e -> {
            try {
                controllerFrame.findPersonAndStartGame(tlogin.getText(), tpassword.getText());
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Message",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        c.add(cont);

        repaint();
        setVisible(true);
    }
}