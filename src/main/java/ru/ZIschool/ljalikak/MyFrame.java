package ru.ZIschool.ljalikak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyFrame
        extends JFrame
        implements ActionListener {

    // Components of the Form
    // insert, update, delete, select
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
    private JButton sub;
    private JButton reset;

    public MyFrame()
    {
        setTitle("Registration Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);


        c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.decode("#212121"));

        title = new JLabel("welcome to the radioactive forest");
        title.setFont(new Font("Helvetica", Font.PLAIN, 30));
        title.setForeground(new Color(20, 240, 116));
        title.setSize(500, 30);
        title.setLocation(300, 30);
        c.add(title);

        login = new JLabel("login");
        login.setFont(new Font("Helvetica", Font.PLAIN, 20));
        login.setForeground(new Color(20, 240, 116));
        login.setSize(100, 20);
        login.setLocation(100, 100);
        c.add(login);

        tlogin = new JTextField();
        tlogin.setFont(new Font("Helvetica", Font.PLAIN, 15));
        tlogin.setSize(190, 20);
        tlogin.setLocation(200, 100);
        c.add(tlogin);

        password = new JLabel("password");
        password.setFont(new Font("Helvetica", Font.PLAIN, 20));
        password.setForeground(new Color(20, 240, 116));
        password.setSize(100, 20);
        password.setLocation(100, 150);
        c.add(password);

        tpassword = new JTextField();
        tpassword.setFont(new Font("Helvetica", Font.PLAIN, 15));
        tpassword.setSize(150, 20);
        tpassword.setLocation(200, 150);
        c.add(tpassword);

        race = new JLabel("race");
        race.setFont(new Font("Helvetica", Font.PLAIN, 20));
        race.setForeground(new Color(20, 240, 116));
        race.setSize(100, 20);
        race.setLocation(100, 200);
        c.add(race);

        human = new JRadioButton("human");
        human.setFont(new Font("Helvetica", Font.PLAIN, 15));
        human.setForeground(new Color(20, 240, 116));
        human.setSelected(true);
        human.setSize(100, 20);
        human.setLocation(200, 200);
        c.add(human);

        mutant = new JRadioButton("mutant");
        mutant.setFont(new Font("Helvetica", Font.PLAIN, 15));
        mutant.setForeground(new Color(20, 240, 116));
        mutant.setSelected(false);
        mutant.setSize(100, 20);
        mutant.setLocation(300, 200);
        c.add(mutant);

        ghoul = new JRadioButton("ghoul");
        ghoul.setFont(new Font("Helvetica", Font.PLAIN, 15));
        ghoul.setForeground(new Color(20, 240, 116));
        ghoul.setSelected(false);
        ghoul.setSize(100, 20);
        ghoul.setLocation(400, 200);
        c.add(ghoul);

        racep = new ButtonGroup();
        racep.add(human);
        racep.add(ghoul);
        racep.add(mutant);

        sub = new JButton("new");
        sub.setForeground(Color.decode("#212121"));
        sub.setFont(new Font("Helvetica", Font.PLAIN, 15));
        sub.setSize(200, 30);
        sub.setLocation(90, 250);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("continue")
        {
            @Override
            public boolean isBackgroundSet() {
                return super.isBackgroundSet();
            }
        };
        reset.setForeground(Color.decode("#ff"));
        reset.setBackground(Color.decode("#ff0000"));
//        reset.setContentAreaFilled(false);
        reset.setOpaque(true);
        reset.setBorderPainted(false);
//        reset.setOpaque();

        reset.setFont(new Font("Helvetica", Font.PLAIN, 15));
        reset.setSize(200, 30);
        reset.setLocation(300, 250);
        reset.addActionListener(this);
        c.add(reset);

        repaint();
        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e)
    {
//        if (e.getSource() == sub) {
//            if (term.isSelected()) {
//                String data1;
//                String data
//                        = "Name : "
//                        + tlogin.getText() + "\n"
//                        + "Mobile : "
//                        + tpassword.getText() + "\n";
//                if (human.isSelected())
//                    data1 = "Gender : Male"
//                            + "\n";
//                else
//                    data1 = "Gender : Female"
//                            + "\n";
//                ;
//                res.setText("Registration Successfully..");
//            }
//            else {
//                resadd.setText("");
//                res.setText("Please accept the"
//                        + " terms & conditions..");
//            }
//        }
//
//        else if (e.getSource() == reset) {
//            String def = "";
//            tlogin.setText(def);
//            tpassword.setText(def);
//            res.setText(def);
//            term.setSelected(false);
//            resadd.setText(def);
//        }
    }
}