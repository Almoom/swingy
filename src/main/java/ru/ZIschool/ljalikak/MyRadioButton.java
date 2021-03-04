package ru.ZIschool.ljalikak;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyRadioButton extends JRadioButton {
    private static final Color color = new Color(20, 240, 116);
    private static Font font;
    private static int count = 0;
    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/14262.ttf"))
                    .deriveFont(15f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public MyRadioButton(String type) {
        super(type);
        setFont(font);
        setForeground(color);
        setSize(80, 20);
        setSelected(type.equals(Types.HUMAN.toString().toLowerCase()));
        setLocation(190 + count * 70, 200);
        count++;
    }
}
