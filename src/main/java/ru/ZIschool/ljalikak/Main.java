package ru.ZIschool.ljalikak;

import ru.ZIschool.ljalikak.controller.ControllerFactory;

import java.util.Scanner;

public class Main {

    private static final String CONSOLE_MODE = "console";
    private static final String GUI_MODE = "gui";

    public static void main(String[] args) {
        ControllerFactory.newController(getMode());
    }

    private static String getMode() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose game mode:");
        System.out.println("1 - \"gui\"");
        System.out.println("2 - \"console\"");

        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            switch (text) {
                case CONSOLE_MODE:
                case "2" : return CONSOLE_MODE;
                case GUI_MODE:
                case "1" : return GUI_MODE;
                default:
                    System.out.println("Choose game mode:");
                    System.out.println("1 - \"gui\"");
                    System.out.println("2 - \"console\"");
                    break;
            }
        }
        return GUI_MODE;
    }
}
