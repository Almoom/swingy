package ru.ZIschool.ljalikak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;

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
        Font h2f = null;
        Font h3f = null;
        try {
            h1f = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Overseer Italic.otf"))
                    .deriveFont(50f);
            h2f = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/14262.ttf"))
                    .deriveFont(20f);
            h3f = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/14262.ttf"))
                    .deriveFont(15f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        setTitle("Registration Form");
        setBounds(300, 90, 490, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.decode("#212121"));
        //#d9af2f - yellow

        title = new JLabel("Pip-boy game");
        title.setFont(h1f);
        title.setForeground(new Color(20, 240, 116));
        title.setSize(500, 60);
        title.setLocation(130, 20);
        c.add(title);

        login = new JLabel("Login");
        login.setFont(h2f);
        login.setForeground(new Color(20, 240, 116));
        login.setSize(100, 30);
        login.setLocation(100, 100);
        c.add(login);

        tlogin = new JTextField();
        tlogin.setFont(new Font("Helvetica", Font.PLAIN, 15));
        tlogin.setSize(190, 20);
        tlogin.setLocation(200, 100);
        c.add(tlogin);

        password = new JLabel("Password");
        password.setFont(h2f);
        password.setForeground(new Color(20, 240, 116));
        password.setSize(100, 30);
        password.setLocation(100, 150);
        c.add(password);

        tpassword = new JTextField();
        tpassword.setFont(new Font("Helvetica", Font.PLAIN, 15));
        tpassword.setSize(190, 20);
        tpassword.setLocation(200, 150);
        c.add(tpassword);

        race = new JLabel("Race");
        race.setFont(h2f);
        race.setForeground(new Color(20, 240, 116));
        race.setSize(100, 30);
        race.setLocation(100, 200);
        c.add(race);

        human = new JRadioButton("human");
        human.setFont(h3f);
        human.setForeground(new Color(20, 240, 116));
        human.setSelected(true);
        human.setSize(80, 20);
        human.setLocation(190, 200);
        human.addActionListener(e -> type = Types.valueOf(e.getActionCommand().toUpperCase()));
        c.add(human);

        mutant = new JRadioButton("mutant");
        mutant.setFont(h3f);
        mutant.setForeground(new Color(20, 240, 116));
        mutant.setSelected(false);
        mutant.setSize(80, 20);
        mutant.setLocation(260, 200);
        mutant.addActionListener(e -> type = Types.valueOf(e.getActionCommand().toUpperCase()));
        c.add(mutant);

        ghoul = new JRadioButton("ghoul");
        ghoul.setFont(h3f);
        ghoul.setForeground(new Color(20, 240, 116));
        ghoul.setSelected(false);
        ghoul.setSize(80, 20);
        ghoul.setLocation(330, 200);
        ghoul.addActionListener(e -> type = Types.valueOf(e.getActionCommand().toUpperCase()));
        c.add(ghoul);

        racep = new ButtonGroup();
        racep.add(human);
        racep.add(ghoul);
        racep.add(mutant);

        create = new JButton("new");
        create.setFont(h3f);
        create.setSize(150, 30);
        create.setLocation(90, 250);
        create.addActionListener(e -> {
            try {
                serviceH2Db.create(new Person(tlogin.getText(), tpassword.getText(), type));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        c.add(create);

        cont = new JButton("continue");
        cont.setFont(h3f);
        cont.setSize(150, 30);
        cont.setLocation(250, 250);
        cont.addActionListener(e -> {
            try {
                System.out.println(serviceH2Db.check(tlogin.getText()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        c.add(cont);

        repaint();
        setVisible(true);
    }
}