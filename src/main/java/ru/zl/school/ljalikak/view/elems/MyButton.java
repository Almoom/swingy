package ru.zl.school.ljalikak.view.elems;

import ru.zl.school.ljalikak.view.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MyButton extends JButton {
    private static int count = 0;
    private static Font font;
    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(MyFrame.class.getResource("/14262.ttf")).openStream())
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
