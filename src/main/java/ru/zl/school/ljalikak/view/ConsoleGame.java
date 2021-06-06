package ru.zl.school.ljalikak.view;

import ru.zl.school.ljalikak.controller.Actions;
import ru.zl.school.ljalikak.controller.ControllerConsole;
import ru.zl.school.ljalikak.model.Dice;
import ru.zl.school.ljalikak.model.Person;
import ru.zl.school.ljalikak.model.Place;
import ru.zl.school.ljalikak.model.Level;

import static ru.zl.school.ljalikak.model.Person.*;

import java.util.Scanner;

public class ConsoleGame {
    private final static String TREE = "\033[2;0;32mT\033[00m";
    private final static String EMPTY = " ";
    private final static String PLAYER = "\033[2;43;30mP\033[00m";
    private final static String ANIMAL = "\033[2;41;30mr\033[00m";
    private final static String STONE = "\033[7;37ms\033[00m";
    private final static String BOUNDARY = "\033[1;30mX\033[00m";

    private Level level;
    private Place[][] env;
    private int width;
    private int height;

//    private ModelView model;
    private ControllerConsole controller;
    private Person person;
    private String[] logs;
    private String emptyLine;

    private static Runnable DEFAULT_ACTION = new Runnable() {
        @Override
        public void run() {}
    };
    private Runnable runner;
    private boolean needDestroy;


    private static String[] LABELS = {NAME, HP, LEVEL, EXP, ATTACK, DEFENSE};

    public Place[][] getEnv() {
        return env;
    }
    public ConsoleGame(ControllerConsole controller, Level level) {
        person = controller.getPerson();
//        this.model = model;
        this.controller = controller;
        this.level = level;
//        runner = DEFAULT_ACTION;

        width = 60;
        height = 20;
        env = new Place[height][width];

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < width + 1; i++) {
            stringBuilder.append(" ");
        }
        emptyLine = stringBuilder.toString();


//        Thread thread = new Thread(this);
//        thread.setName("Terminal");
//        thread.start();
    }

    private String getText(int i, int j) {

//        if (env[i][j].hasWarrior()) {
//            switch (env[i][j].getWarrior().getClazz()) {
//                case SALAMANDER: return SALAMANDER;
//                case CAPYBARA: return CAPYBARA;
//                case ALPACA: return ALPACA;
//                case HONEY_BADGER: return HONEY_BADGER;
//                default:
//                    return EMPTY;
//            }
//        }

        switch (env[i][j].getObject().getTypes()) {
            case BOUNDARY: return BOUNDARY;
            case STONE: return STONE;
            case TREE: return TREE;
            case PLAYER: return PLAYER;
            case ANIMAL: return ANIMAL;
            default:
                return EMPTY;
        }
    }

    public void refresh() {


        level.fillEnvironment(env);


        System.out.println("\33c");

//        player = model.getPlayer();
//        logs = Fighting.getTextLog().split("\n");
//        model.fillEnvironment(env);

        String text = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                text = getText(i, j);
                if (i == height / 2 && j == width / 2) {
                    text = getText(i, j);
                }
                System.out.print(text);
            }
//            printParamOrLogs(i);
            System.out.println("");
        }
//        for (int i = height - LABELS.length; i < logs.length; i++) {
//            System.out.print(emptyLine);
//            System.out.println(logs[i]);
//        }
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
        } else if (i < LABELS.length + logs.length) {
            i -= LABELS.length;
            System.out.print(" ");
            System.out.print(logs[i]);
        }
    }

//    public void run() {
//        while (!needDestroy) {
//            runner.run();
//
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }



    public void gameLogic() {
        refresh();
        while (true) {



            try {
                Actions action = Actions.getAction(readLine());
                controller.executeCommand(action);
                refresh();
//                if (controller.isMeetEnemy(action) && !confirm("Do you want fight?") && Dice.d2()) {
//                    return;
//                } else {

//                            getReward();
//                            controller.moveWorld();
//                            getReward();
//                }

            } catch (DeadException e) {
                refresh();
//                    if (confirm(e.getMessage() + "\nrestart level?")) {
//                        controller.startGame();
//                    } else {
//                        controller.startMenu();
//                    }
            }
        }
    }








    public void startGame() {
        runner = new Runnable() {
            @Override
            public void run() {
                refresh();
                try {
                    Actions action = Actions.getAction(readLine());
                    if (controller.isMeetEnemy(action) && !confirm("Do you want fight?") && Dice.d2()) {
                        return;
                    } else {
//                        if (controller.executeCommand(action)) {
//                            getReward();
//                            controller.moveWorld();
//                            getReward();
//                        }
                    }
                } catch (DeadException e) {
                    refresh();
//                    if (confirm(e.getMessage() + "\nrestart level?")) {
//                        controller.startGame();
//                    } else {
//                        controller.startMenu();
//                    }
                }
            }
        };
    }

//    private void getReward() {
//        while (model.hasItems()) {
//            Item item = model.getItem();
//            if (confirm("Do you want equip " + item.getName() + "?")) {
//                item.equip(model.getPlayer());
//            }
//        }
//    }

    private boolean confirm(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        System.out.println("yes/no");
        while (scanner.hasNext()) {
            String text = scanner.nextLine().trim();

            switch (text) {
                case "y":
                case "yes": return true;
                case "n":
                case "no": return false;
                default:
                    System.out.println("print \"yes\" or \"no\"");
                    break;
            }
        }
        return true;
    }

    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    public void destroy() {
        needDestroy = true;
    }
}
