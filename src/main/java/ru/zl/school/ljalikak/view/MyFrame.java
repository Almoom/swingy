package ru.zl.school.ljalikak.view;

import ru.zl.school.ljalikak.Person;
import ru.zl.school.ljalikak.Race;
import ru.zl.school.ljalikak.controller.ControllerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyFrame extends JFrame {
    ControllerGUI controllerFrame;
    private MyPanel gamePanel;
    private JPanel textPanel;
    private KeyListener listener;
    private Map<String, JLabel> labels;
    private static final String[] LABELS = {"NAME", "HP", "LEVEL", "EXP", "next exp", "ATTACK", "DEFENSE", "HELMET"};
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

    public MyFrame(ControllerGUI controllerFrame) throws IOException {

        this.controllerFrame = controllerFrame;
        gamePanel = new MyPanel(HELLO_PANEL_WIDTH, HELLO_PANEL_HEIGHT);
        gamePanel.setBounds(0, 0, HELLO_PANEL_WIDTH, HELLO_PANEL_HEIGHT);
        gamePanel.setBackground(new Color(20, 240, 116));

        textPanel = createTextPane();
        textPanel.setBounds(GAME_PANEL_WIDTH + 10, 10, TEXT_WIDTH - 20, HELLO_PANEL_HEIGHT - 40);

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

    public void repainForGame(Person person) {
        getContentPane().removeAll();
        setBounds(getX(), getY(), GAME_PANEL_WIDTH + TEXT_PANEL_WIDTH, HELLO_PANEL_HEIGHT);
        gamePanel.setBounds(0, 0, GAME_PANEL_WIDTH , HELLO_PANEL_HEIGHT);
        add(gamePanel);
        add(textPanel);
        addKeyListener(listener);

        refresh();
    }

    private JPanel createTextPane() {
        JPanel panel = new JPanel(null);
        panel.setSize(TEXT_PANEL_WIDTH - 5, HELLO_PANEL_HEIGHT);
        panel.setBackground(new Color(217, 175, 47));

        labels = new HashMap<>();

        int y = TEXT_START_Y;
        for (String s : LABELS) {
            JLabel label = new JLabel(s);
            label.setBounds(TEXT_START_X, y, TEXT_STEP_X, TEXT_START_Y);
            panel.add(label);

            label = new JLabel(s);
            label.setBounds(TEXT_START_X + TEXT_STEP_X, y, TEXT_STEP_X, TEXT_START_Y);
            panel.add(label);
            labels.put(s, label);

            y += TEXT_STEP_Y;
        }

//        JLabel label = new JLabel("logger");
//        label.setPreferredSize(new Dimension( TEXT_PANEL_WIDTH - 40,2000));
//
//        JScrollPane scrollFrame = new JScrollPane();
//        label.setAutoscrolls(true);
//        scrollFrame.add(label);
//        scrollFrame.setPreferredSize(new Dimension( TEXT_PANEL_WIDTH - 40,100));
//        scrollFrame.setBounds(10, y, TEXT_PANEL_WIDTH - 50, 300);
//        panel.add(scrollFrame);

//        label.setBounds(20, y, TEXT_PANEL_WIDTH - 40, 300);
//        label.setVerticalAlignment(SwingConstants.TOP);
//        panel.add(label);
//        labels.put("logger", label);

        return  panel;
    }

    public void refresh() {

//        model.fillEnvironment(gamePanel.getEnv());
//        Warrior person = model.getPlayer();
//        updateField(NAME, person.getName());
//        updateField(HP, Integer.toString(person.getHp()));
//        updateField(LEVEL, Integer.toString(person.getLevel()));
//        updateField(EXP, Integer.toString(person.getExperience()));
//        updateField("next exp", Integer.toString(person.expNextLevel));
//        updateField(DEFENSE, Integer.toString(person.getDefence()));
//        updateField(HELMET, Integer.toString(person.getHelmet()));
//        updateField(ATTACK, Integer.toString(person.getAttack()));
//        updateField("logger", "<html>".concat(person.getLog().replace("\n", "<br>").concat("</html>")));

        repaint();
    }
}