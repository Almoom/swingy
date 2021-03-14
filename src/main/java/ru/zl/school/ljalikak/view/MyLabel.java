package ru.zl.school.ljalikak.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyLabel extends JLabel {
    private static final Color color = new Color(20, 240, 116);
    private static int count = 0;
    private static Font font;
    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/14262.ttf"))
                    .deriveFont(20f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public MyLabel(String text) {
        super(text);
        setSize(100, 30);
        setForeground(color);
        setLocation(100, 100 + 50 * count++);
        setFont(font);
    }
}
