package ru.zl.school.ljalikak.view.elems;

import ru.zl.school.ljalikak.model.Types;
import ru.zl.school.ljalikak.view.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MyRadioButton extends JRadioButton {
    private static final Color color = new Color(20, 240, 116);
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

    public MyRadioButton(String text) {
        super(text);
        setFont(font);
        setForeground(color);
        setSize(80, 20);
        setSelected(text.equals(Types.HUMAN.toString().toLowerCase()));
        setLocation(190 + count++ * 70, 138);
    }
}
