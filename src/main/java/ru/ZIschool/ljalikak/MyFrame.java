package ru.ZIschool.ljalikak;

import ru.ZIschool.ljalikak.controller.ControllerFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class MyFrame extends JFrame {
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
    private Types type;
    private ControllerFrame controllerFrame = new ControllerFrame();

    public MyFrame() {

        Font h1f = null;
        try {
            h1f = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Overseer Italic.otf"))
                    .deriveFont(50f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        setTitle("Registration Form");
        setBounds(300, 90, 490, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(33, 33, 33));
        //#d9af2f - yellow

        title = new JLabel("Pip-boy game");
        title.setFont(h1f);
        title.setForeground(new Color(20, 240, 116));
        title.setSize(500, 60);
        title.setLocation(130, 20);
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

        human = new MyRadioButton(Types.HUMAN.toString().toLowerCase());
        human.addActionListener(e -> type = Types.valueOf(e.getActionCommand().toUpperCase()));
        c.add(human);

        mutant = new MyRadioButton(Types.MUTANT.toString().toLowerCase());
        mutant.addActionListener(e -> type = Types.valueOf(e.getActionCommand().toUpperCase()));
        c.add(mutant);

        ghoul = new MyRadioButton(Types.GHOUL.toString().toLowerCase());
        ghoul.addActionListener(e -> type = Types.valueOf(e.getActionCommand().toUpperCase()));
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