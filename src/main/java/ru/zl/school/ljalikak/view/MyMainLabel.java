package ru.zl.school.ljalikak.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyMainLabel extends JLabel {
    private static final Color color = new Color(20, 240, 116);
    private static Font font;
    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Overseer Italic.otf"))
                    .deriveFont(50f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public MyMainLabel(String text) {
        super(text);
        setFont(font);
        setForeground(color);
        setSize(500, 60);
        setLocation(130, 10);
    }
}
