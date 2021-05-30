package ru.zl.school.ljalikak.view;

import ru.zl.school.ljalikak.model.Person;
import ru.zl.school.ljalikak.model.Race;
import ru.zl.school.ljalikak.controller.Actions;
import ru.zl.school.ljalikak.controller.ControllerGUI;
import ru.zl.school.ljalikak.view.elems.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.zl.school.ljalikak.model.Person.*;

public class MyFrame extends JFrame {
    ControllerGUI controllerFrame;
    private Person person;
    private MyPanel gamePanel;
    private JPanel textPanel;
    private KeyListener listener;
    private Map<String, JLabel> labels;
    private static final String[] LABELS = {NAME, HP, LEVEL, EXP, ATTACK, DEFENSE};
    private static final int HELLO_PANEL_HEIGHT = 352;
    private static final int HELLO_PANEL_WIDTH = 490;
    private static final int GAME_PANEL_WIDTH = 330;
    private static final int TEXT_PANEL_WIDTH = 300;

    private static int TEXT_WIDTH = 300;
    private static int TEXT_START_X = 20;
    private static int TEXT_START_Y = 20;
    private static int TEXT_STEP_X = 100;
    private static int TEXT_STEP_Y = 30;

    private Container c;
    private JLabel title;
    private JLabel login;
    private JTextField tlogin;
    private JLabel race;
    private JLabel prepared;
    private JRadioButton human;
    private JRadioButton mutant;
    private JRadioButton ghoul;
    private ButtonGroup racep;
    private JButton create;
    private JButton cont;
    private JButton prepMen;
    private JButton prepWomen;
    private JButton prepDog;
    private Race type = Race.HUMAN;

    private static Font font;
    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/14262.ttf"))
                    .deriveFont(20f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public MyFrame(ControllerGUI controllerFrame) throws IOException {

        this.controllerFrame = controllerFrame;
        gamePanel = new MyPanel(GAME_PANEL_WIDTH, GAME_PANEL_WIDTH);
        gamePanel.setBounds(0, 0, HELLO_PANEL_WIDTH, HELLO_PANEL_HEIGHT);
        gamePanel.setBackground(new Color(20, 240, 116));

        textPanel = createTextPane();
        textPanel.setBounds(GAME_PANEL_WIDTH + 10, 10, TEXT_WIDTH - 20, HELLO_PANEL_HEIGHT - 40);

        controllerFrame.setEnv(gamePanel.getEnv());

        listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                Actions action = Actions.getAction(e.getKeyChar());
                try {
                    controllerFrame.executeCommand(action);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Player is dead!!!",
                            "Game Over",
                            JOptionPane.PLAIN_MESSAGE);
                    controllerFrame.exit();
                }
                refresh(person);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };

        setTitle("Swingy by Ljalikak");
        setBounds(500, 200, HELLO_PANEL_WIDTH, HELLO_PANEL_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(33, 33, 33));

        title = new MyMainLabel("Pip-boy game");
        c.add(title);
        login = new MyLabel("Login");
        c.add(login);
        tlogin = new MyTextField();
        c.add(tlogin);
        race = new MyLabel("Race");
        c.add(race);
        prepared = new MyLabel("<html>Or use</br> prepared</html>");
        c.add(prepared);

        human = new MyRadioButton(Race.HUMAN.toString().toLowerCase());
        human.addActionListener(e -> type = Race.valueOf(e.getActionCommand().toUpperCase()));
        human.setToolTipText("Начните игру человеком из Мегатонны с уровня 1!");
        c.add(human);

        ghoul = new MyRadioButton(Race.GHOUL.toString().toLowerCase());
        ghoul.addActionListener(e -> type = Race.valueOf(e.getActionCommand().toUpperCase()));
        ghoul.setToolTipText("Начните игру гулем из Некрополиса с уровня 5!");
        c.add(ghoul);

        mutant = new MyRadioButton(Race.MUTANT.toString().toLowerCase());
        mutant.addActionListener(e -> type = Race.valueOf(e.getActionCommand().toUpperCase()));
        mutant.setToolTipText("Начните игру мутантом из Мирапозы с уровня 10!");
        c.add(mutant);

        racep = new ButtonGroup();
        racep.add(human);
        racep.add(ghoul);
        racep.add(mutant);

        prepMen = new MyPrepButton(MyFrame.class.getResource("/men.png"));
        prepMen.addActionListener(e -> startNew(new Person(tlogin.getText(), Race.HUMAN, 1, 0, 2, 2, 10)));
        prepMen.setToolTipText("Вы мужик 1 уровня, с атакой 2, защитой 2 и 10 жизнями");

        prepDog = new MyPrepButton(MyFrame.class.getResource("/dog.png"));
        prepDog.addActionListener(e -> startNew(new Person(tlogin.getText(), Race.HUMAN, 1, 0, 2, 1, 15)));
        prepDog.setToolTipText("Вы пёс 1 уровня, с атакой 2, защитой 1 и 15 жизнями");

        prepWomen = new MyPrepButton(MyFrame.class.getResource("/women.png"));
        prepWomen.addActionListener(e -> startNew(new Person(tlogin.getText(), Race.HUMAN, 1, 0, 1, 2, 15)));
        prepWomen.setToolTipText("Вы женщина 1 уровня, с атакой 1, защитой 2 и 15 жизнями");

        c.add(prepMen);
        c.add(prepDog);
        c.add(prepWomen);


        create = new MyButton("new");
        create.addActionListener(e -> startNew(new Person(tlogin.getText(), type)));
        c.add(create);

        cont = new MyButton("continue");
        cont.addActionListener(e -> {
            try {
                if (tlogin.getText().isEmpty()) {
                    printMsg("Введите логин");
                } else {
                    controllerFrame.findPersonAndStartGame(tlogin.getText());
                    person = controllerFrame.getPerson();
                }
            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }
        });
        c.add(cont);

        repaint();
        setVisible(true);
    }

    public void startNew(Person person) {
        try {
            this.person = person;
            if (tlogin.getText().isEmpty()) {
                printMsg("Введите логин");
            } else {
                controllerFrame.createNewPersonAndStartGame(person);
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    public void printMsg(String msg) {
        JOptionPane.showMessageDialog(null,
                msg,
                "Please, stand by...",
                JOptionPane.PLAIN_MESSAGE);
    }

    public void repainForGame(Person person) {
        getContentPane().removeAll();
        dispose();
        setBounds(getX(), getY(), GAME_PANEL_WIDTH + TEXT_PANEL_WIDTH, HELLO_PANEL_HEIGHT);
        gamePanel.setBounds(0, 0, GAME_PANEL_WIDTH , HELLO_PANEL_HEIGHT);
        add(gamePanel);
        add(textPanel);
        addKeyListener(listener);

        refresh(person);
        setVisible(true);
    }

    private JPanel createTextPane() {
        JPanel panel = new JPanel(null);
        panel.setSize(TEXT_PANEL_WIDTH - 5, HELLO_PANEL_HEIGHT);
        panel.setBackground(new Color(77, 66, 52));

        labels = new HashMap<>();

        int y = TEXT_START_Y;
        for (String s : LABELS) {
            JLabel label = new JLabel(s);
            label.setForeground(new Color(217, 175, 47));
            label.setFont(font);
            label.setBounds(TEXT_START_X, y, TEXT_STEP_X, TEXT_START_Y);
            panel.add(label);

            label = new JLabel(s);
            label.setBounds(TEXT_START_X + TEXT_STEP_X, y, TEXT_STEP_X, TEXT_START_Y);
            panel.add(label);
            labels.put(s, label);

            y += TEXT_STEP_Y;
        }

        return  panel;
    }

    public void refresh(Person person) {

        controllerFrame.refresh();

        updateField(NAME, person.getLogin());
        updateField(HP, Integer.toString(person.getHitPoints()));
        updateField(LEVEL, Integer.toString(person.getLevel()));
        updateField(EXP, person.getExperience() + "/" + person.getExpNextLevel());
        updateField(DEFENSE, Integer.toString(person.getDefense()));
        updateField(ATTACK, Integer.toString(person.getAttack()));

        repaint();
    }

    public void updateField(String name, String value) {
        labels.get(name).setText(value);
    }
}