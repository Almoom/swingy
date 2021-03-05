package ru.ZIschool.ljalikak;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

class MyFrame extends JFrame {
    private Container c;
    private JLabel title;
    private JLabel login = new MyLabel("Login");
    private JTextField tlogin = new MyTextField();
    private JLabel password = new MyLabel("Password");
    private JTextField tpassword = new MyTextField();
    private JLabel race = new MyLabel("Race");
    private JRadioButton human;
    private JRadioButton mutant;
    private JRadioButton ghoul;
    private ButtonGroup racep;
    private JButton create;
    private JButton cont;
    private Types type;
    public static ServiceH2Db serviceH2Db;

    static {
        try {
            serviceH2Db = new ServiceH2Db();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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

        c.add(login);
        c.add(tlogin);
        c.add(password);
        c.add(tpassword);
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
                serviceH2Db.create(new Person(tlogin.getText(), tpassword.getText(), type));
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
                serviceH2Db.read(new Person(tlogin.getText(), tpassword.getText(), type));
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