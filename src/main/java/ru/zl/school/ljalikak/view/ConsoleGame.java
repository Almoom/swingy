package ru.zl.school.ljalikak.view;

import ru.zl.school.ljalikak.controller.Actions;
import ru.zl.school.ljalikak.controller.ControllerConsole;
import ru.zl.school.ljalikak.model.Level;
import ru.zl.school.ljalikak.model.Person;
import ru.zl.school.ljalikak.model.Place;
import ru.zl.school.ljalikak.view.errors.DeadException;

import java.util.Scanner;

import static ru.zl.school.ljalikak.model.Person.*;

public class ConsoleGame {
    private final static String RAD = "\033[2;0;32mW\033[00m";
    private final static String EMPTY = " ";
    private final static String PLAYER = "\033[2;43;30mP\033[00m";
    private final static String RAT = "\033[2;41;30mr\033[00m";
    private final static String MOUNT = "\033[7;37mM\033[00m";
    private final static String BOUNDARY = "\033[1;30mX\033[00m";

    private Level level;
    private Place[][] env;
    private int width;
    private int height;

    private ControllerConsole controller;
    private Person person;

    private static String[] LABELS = {NAME, HP, LEVEL, EXP, ATTACK, DEFENSE};

    public ConsoleGame(ControllerConsole controller, Level level) {
        person = controller.getPerson();
        this.controller = controller;
        this.level = level;

        width = 60;
        height = 20;
        env = new Place[height][width];

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < width + 1; i++) {
            stringBuilder.append(" ");
        }
    }

    private String getText(int i, int j) {
        switch (env[i][j].getObject().getTypes()) {
            case BOUNDARY: return BOUNDARY;
            case MOUNT: return MOUNT;
            case RAD: return RAD;
            case PLAYER: return PLAYER;
            case RAT: return RAT;
            default:
                return EMPTY;
        }
    }

    public void refresh() {
        level.fillEnvironment(env);
        System.out.println("\33c");
        System.out.println("[Для выхода введите \"exit\"]");

        String text = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                text = getText(i, j);
                if (i == height / 2 && j == width / 2) {
                    text = getText(i, j);
                }
                System.out.print(text);
            }
            printParamOrLogs(i);
            System.out.println("");
        }
    }

    private void printParamOrLogs(int i) {
        if (i < LABELS.length) {
            String label = LABELS[i];
            System.out.printf(" %15s  ", label);
            switch (label) {
                case NAME:
                    System.out.print(person.getLogin());
                    break;
                case HP:
                    System.out.print(person.getHitPoints());
                    break;
                case LEVEL:
                    System.out.print(person.getLevel());
                    break;
                case EXP:
                    System.out.print(person.getExperience());
                    System.out.print('/');
                    System.out.print(person.getExpNextLevel());
                    break;
                case DEFENSE:
                    System.out.print(person.getDefense());
                    break;
                case ATTACK:
                    System.out.print(person.getAttack());
                    break;
                default:
                    break;
            }
        }
    }

    public void gameLogic() {
        refresh();
        while (true) {
            try {
                Actions action = Actions.getAction(readLine());
                if (action.equals(Actions.EXIT)) {
                    controller.exit();
                }
                controller.executeCommand(action);
                refresh();
            } catch (DeadException e) {
                System.out.println("Вы умерли. Ваш облучённый труп не заинтересовал даже стервятников.");
                controller.exit();
            }
        }
    }

    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
