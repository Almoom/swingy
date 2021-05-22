package ru.zl.school.ljalikak.view;

import javax.swing.*;

public class AuthException extends RuntimeException {
    public AuthException(RuntimeException ex) {
        JOptionPane.showMessageDialog(null,
                ex.getMessage(),
                "Message",
                JOptionPane.PLAIN_MESSAGE);
    }

    public AuthException(String ex) {
        JOptionPane.showMessageDialog(null,
                ex,
                "Message",
                JOptionPane.PLAIN_MESSAGE);
    }
}
