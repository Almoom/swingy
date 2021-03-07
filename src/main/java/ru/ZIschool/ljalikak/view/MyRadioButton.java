package ru.ZIschool.ljalikak.view;

import ru.ZIschool.ljalikak.Race;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyRadioButton extends JRadioButton {
    private static final Color color = new Color(20, 240, 116);
    private static int count = 0;
    private static Font font;
    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/14262.ttf"))
                    .deriveFont(15f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public MyRadioButton(String text) {
        super(text);
        setFont(font);
        setForeground(color);
        setSize(80, 20);
        setSelected(text.equals(Race.HUMAN.toString().toLowerCase()));
        setLocation(190 + count++ * 70, 200);
    }
}
