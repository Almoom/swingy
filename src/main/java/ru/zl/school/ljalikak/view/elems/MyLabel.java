package ru.zl.school.ljalikak.view.elems;

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
        if (text.contains("</br>")) {
            setSize(100, 50);
            setLocation(100, 80 + 50 * count++);
        } else {
            setSize(100, 30);
            setLocation(100, 85 + 50 * count++);
        }
        setForeground(color);
        setFont(font);
    }
}
