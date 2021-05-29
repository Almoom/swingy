package ru.zl.school.ljalikak.view.elems;

import ru.zl.school.ljalikak.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MyPrepButton extends JButton {
    private static int count = 0;

    public MyPrepButton(URL imgURL) {
        ImageIcon icon = new ImageIcon(imgURL);
        setIcon(icon);
        setSize(50, 50);
        setLocation(200 + 70 * count++, 180);
    }
}
