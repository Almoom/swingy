package ru.zl.school.ljalikak.view.guiElems;

import javax.swing.*;
import java.net.URL;

public class GuiPrepButton extends JButton {
    private static int count = 0;

    public GuiPrepButton(URL imgURL) {
        ImageIcon icon = new ImageIcon(imgURL);
        setIcon(icon);
        setSize(50, 50);
        setLocation(200 + 70 * count++, 180);
    }
}
