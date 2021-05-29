package ru.zl.school.ljalikak.view.elems;

import javax.swing.*;
import java.awt.*;

public class MyTextField extends JTextField {
    private static final Font font = new Font("Helvetica", Font.PLAIN, 15);
    private static int count = 0;

    public MyTextField() {
        super();
        setFont(font);
        setSize(190, 20);
        setLocation(200, 85 + 50 * count++);
    }
}
