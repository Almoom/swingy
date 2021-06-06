package ru.zl.school.ljalikak.view;

import ru.zl.school.ljalikak.controller.ControllerConsole;
import ru.zl.school.ljalikak.model.Person;
import ru.zl.school.ljalikak.model.Types;

import java.util.Scanner;

public class ConsoleReg {

    Scanner scanner = new Scanner(System.in);

    public ConsoleReg(ControllerConsole controllerFrame) {
        System.out.println("[Для выхода введите \"exit\"]");
        System.out.println("Введите логин:");
        String login = readLine();

        if (login.equals("exit")) return;

        System.out.println("[Для выхода введите \"exit\"]");
        System.out.println("Создайте персонажа...");
        System.out.println("1 - Начните игру человеком из Мегатонны с уровня 1!");
        System.out.println("2 - Начните игру гулем из Некрополиса с уровня 5!");
        System.out.println("3 - Начните игру мутантом из Мирапозы с уровня 10!");
        System.out.println("...Или выберите предсозданного персонажа!");
        System.out.println("4 - Вы мужик 1 уровня, с атакой 2, защитой 2 и 10 жизнями!");
        System.out.println("5 - Вы пёс 1 уровня, с атакой 2, защитой 1 и 15 жизнями!");
        System.out.println("6 - Вы женщина 1 уровня, с атакой 1, защитой 2 и 15 жизнями!");
        System.out.println("Либо:");
        System.out.println("7 - Продолжить игру...");

        while (scanner.hasNext()) {
            String text = readLine();
            if (text.equals("exit")) return;
            switch (text) {
                case "1": if (!controllerFrame.createNewPersonAndStartGame(new Person(login, Types.HUMAN))) new ConsoleReg(controllerFrame); break;
                case "2": if (!controllerFrame.createNewPersonAndStartGame(new Person(login, Types.GHOUL))) new ConsoleReg(controllerFrame); break;
                case "3": if (!controllerFrame.createNewPersonAndStartGame(new Person(login, Types.MUTANT))) new ConsoleReg(controllerFrame); break;
                case "4": if (!controllerFrame.createNewPersonAndStartGame(new Person(login, Types.HUMAN, 1, 0, 2, 2, 10))) new ConsoleReg(controllerFrame); break;
                case "5": if (!controllerFrame.createNewPersonAndStartGame(new Person(login, Types.HUMAN, 1, 0, 2, 1, 15))) new ConsoleReg(controllerFrame); break;
                case "6": if (!controllerFrame.createNewPersonAndStartGame(new Person(login, Types.HUMAN, 1, 0, 1, 2, 15))) new ConsoleReg(controllerFrame); break;
                case "7": if (!controllerFrame.findPersonAndStartGame(login)) new ConsoleReg(controllerFrame); break;
                default:
                    System.out.println("[Для выхода введите \"exit\"]");
                    System.out.println("Создайте персонажа...");
                    System.out.println("1 - Начните игру человеком из Мегатонны с уровня 1!");
                    System.out.println("2 - Начните игру гулем из Некрополиса с уровня 5!");
                    System.out.println("3 - Начните игру мутантом из Мирапозы с уровня 10!");
                    System.out.println("...Или выберите предсозданного персонажа!");
                    System.out.println("4 - Вы мужик 1 уровня, с атакой 2, защитой 2 и 10 жизнями!");
                    System.out.println("5 - Вы пёс 1 уровня, с атакой 2, защитой 1 и 15 жизнями!");
                    System.out.println("6 - Вы женщина 1 уровня, с атакой 1, защитой 2 и 15 жизнями!");
                    System.out.println("Либо:");
                    System.out.println("7 - Продолжить игру...");
                    break;
            }
        }

    }

    private String readLine() {
        return scanner.nextLine().trim();
    }
}
