package ru.zl.school.ljalikak;

import ru.zl.school.ljalikak.controller.ControllerFactory;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args[0].equals("gui") || args[0].equals("console")) {
            ControllerFactory.newController(args[0]);
        } else {
            System.out.println("Некорректный ввод.");
            System.out.println("Выберите один из двух режимов игры:");
            System.out.println("java -jar target/swingy.jar gui");
            System.out.println("java -jar target/swingy.jar console");

        }
    }
}
