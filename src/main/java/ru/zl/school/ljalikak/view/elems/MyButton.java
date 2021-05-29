package ru.zl.school.ljalikak.view.elems;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyButton extends JButton {
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

    public MyButton(String text) {
        super(text);
        setSize(150, 30);
        setLocation(90 + 160 * count++, 250);
        setFont(font);
    }

}
