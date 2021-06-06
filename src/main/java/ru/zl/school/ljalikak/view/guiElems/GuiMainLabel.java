package ru.zl.school.ljalikak.view.guiElems;

import ru.zl.school.ljalikak.view.GuiFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class GuiMainLabel extends JLabel {
    private static final Color color = new Color(20, 240, 116);
    private static Font font;
    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(GuiFrame.class.getResource("/Overseer Italic.otf")).openStream())
                    .deriveFont(50f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public GuiMainLabel(String text) {
        super(text);
        setFont(font);
        setForeground(color);
        setSize(500, 60);
        setLocation(130, 10);
    }
}
