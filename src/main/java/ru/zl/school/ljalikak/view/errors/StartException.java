package ru.zl.school.ljalikak.view.errors;

import javax.swing.*;

public class StartException extends RuntimeException{
    public StartException(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "Please, stand by...",
                JOptionPane.PLAIN_MESSAGE);
    }
}
