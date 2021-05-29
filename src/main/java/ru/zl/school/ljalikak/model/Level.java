package ru.zl.school.ljalikak.model;

import ru.zl.school.ljalikak.Person;
import ru.zl.school.ljalikak.Place;
import ru.zl.school.ljalikak.Race;
import ru.zl.school.ljalikak.Types;
import ru.zl.school.ljalikak.view.PlaceHolder;
import ru.zl.school.ljalikak.view.Warrior;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Level {
    final static public Point LEFT = new Point(-1, 0);
    final static public Point RIGHT = new Point(1, 0);
    final static public Point UP = new Point(0, -1);
    final static public Point DOWN = new Point(0, 1);

    final static private PlaceHolder EMPTY = new PlaceHolder(Types.EMPTY);
    final static private PlaceHolder BOUNDARY = new PlaceHolder(Types.BOUNDARY);
    final static private Place OUT = new Place(BOUNDARY, Types.BLACK);

    private final int SIZE = 11;
    private int width;
    private int height;
    private int level;

    private Place[][] map;
    private List<Warrior> animals;
    private Person player;
    public Person enemy;

    private Random random;

    private StringBuilder logger;
    private boolean loggerOn;

    public Level(Person player) {
        level = player.getLevel();
        int size = getSize(level);
        this.width = size;
        this.height = size;

        initMap();
        player.setXY(size/2,size/2);
        setPlayer(player);

        logger = new StringBuilder("");
    }

    private int getSize(int level) {
        return (level - 1) * 5 + 10 - (level % 2);
    }

    public void setPlayer(Person player) {
        this.player = player;
        insertOnMap(player);
    }

    public void initMap() {
        random = new Random();
        animals = new ArrayList<>();
        map = new Place[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = new Place(EMPTY);
                if (i == SIZE / 2 && j == SIZE / 2)
                    continue;
                int tmp = random.nextInt(100);
                if (tmp < 4) {
                    animals.add(new Warrior("Salamander", Types.ANIMAL, j, i, level));
                    insertOnMap(animals.get(animals.size() - 1));
                } else if (tmp < 7) {
                    insertOnMap(new PlaceHolder(Types.STONE, j, i));
                } else if (tmp < 10) {
                    insertOnMap(new PlaceHolder(Types.TREE, j, i));
                }
            }
        }
    }

    private void insertOnMap(PlaceHolder object, int x, int y) {
        if (y >= 0 && y < SIZE && x >= 0 && x < SIZE) {
            map[y][x].setObject(object);
            if (object != EMPTY) {
                object.setXY(x, y);
            }
        }
    }

    private void insertOnMap(PlaceHolder object) {
        int x = object.getX();
        int y = object.getY();
        insertOnMap(object, x, y);
    }

    private Place getPlace(int x, int y) {
        if (y >= 0 && y < height && x >= 0 && x < width)
            return map[y][x];
        return OUT;
    }

    private PlaceHolder getPlaceHolder(int x, int y) {
        return  getPlace(x, y).getObject();
    }

    public void fillEnvironment(Place[][] env) {
        int height = env.length;
        int width = env[0].length;
        Point center = player.getPos();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int y = center.y + i - height / 2;
                int x = center.x + j - width / 2;

                env[i][j] = getPlace(x, y);
            }
        }
    }

    public boolean isLeaveLevel(Point shift) {
        return getPlaceHolder(player.getX() + shift.x, player.getY() + shift.y) == BOUNDARY;
    }

//    public void tryMoveObject(Warrior warrior, Point shift)  {
//        PlaceHolder current = getPlaceHolder(warrior.getX() + shift.x, warrior.getY() + shift.y);
//        if (current == EMPTY) {
//            Point pos = warrior.getPos();
//            insertOnMap(EMPTY, pos.x, pos.y);
//            pos.translate(shift.x, shift.y);
//            insertOnMap(warrior);
//        } else if (current instanceof Warrior) {
//            Warrior enemy = (Warrior) current;
//            insertOnMap(EMPTY, warrior.getX(), warrior.getY());
//            Warrior winner = warrior.fight(enemy);
//            insertOnMap(winner, enemy.getX(), enemy.getY());
//            getPlace(winner.getX(), winner.getY()).setType(Types.BLOOD);
//        }
//    }

    public void tryMovePerson(Point shift) {
        Place place = getPlace(shift.x + player.getX(), shift.y + player.getY());
        if (place != OUT && place.getObject() == EMPTY) {
            insertOnMap(EMPTY, player.getX(), player.getY());
            player.setXY(shift.x + player.getX(), shift.y + player.getY());
            insertOnMap(player);
        } else if (place.getObject().getTypes() == Types.ANIMAL) {
            enemy = new Person("rat", Race.HUMAN, 1, 1, 1, 1, 5);
            enemy.setXY(shift.x + player.getX(), shift.y + player.getY());
            fight();
//                insertOnMap(EMPTY, player.getX(), player.getY());
//                insertOnMap(player);
//                getPlace(winner.getX(), winner.getY()).setType(Types.BLOOD);

//            Warrior enemy = (Warrior) current;
//            insertOnMap(EMPTY, warrior.getX(), warrior.getY());
//            Warrior winner = warrior.fight(enemy);
//            insertOnMap(winner, enemy.getX(), enemy.getY());
//            getPlace(winner.getX(), winner.getY()).setType(Types.BLOOD);
        }
    }

    public void fight() {
//        enemyLogger = enemy.logger;
//        enemy.enemyLogger = logger;
//        clearLogger();
        while (isAlive(player) && isAlive(enemy)) {
            attack(true);
            if (isAlive(enemy))
                attack(false);
        }

        if (isAlive(player)) {
//            log(name, " kill ", enemy.getName(), "!!!\n");
            player.setExperience(player.getExperience() + enemy.getExperience());
//            winner = this;
            insertOnMap(EMPTY, player.getX(), player.getY());
//            Warrior winner = warrior.fight(enemy);
            player.setXY(enemy.getX(), enemy.getY());
            insertOnMap(player, enemy.getX(), enemy.getY());

//            insertOnMap(player);
//            getPlace(player.getX(), player.getY()).setType(Types.BLOOD);
        } /*else {
//            log(enemy.getName(), " kill ", name, "!!!\n");
            enemy.addExperience(getExperience());
            winner = enemy;
        }*/
//        enemy.enemyLogger = null;
//        enemyLogger = null;
    }

    public int attack(int attack) {
        return (random.nextInt(attack + 2));
    }

    private void attack(boolean im) {
        if (im) {
            int damage = attack(player.getAttack());

//        log(name, " attack ", enemy.getName(), "\n");
//        log("(hp=", String.valueOf(enemy.getHp()), " - ( ", String.valueOf(damage), " - ", String.valueOf(enemy.getDefence()), " ) = ");

            damage -= enemy.getDefense();
            System.out.println("you att = " + damage);
            if (damage > 0) {
                enemy.setHitPoints(enemy.getHitPoints() - damage);
                System.out.println("en hp = " + enemy.getHitPoints());
            }

//        log( String.valueOf(enemy.getHp()), ")\n");
        } else {
            int damage = attack(enemy.getAttack());

//        log(name, " attack ", enemy.getName(), "\n");
//        log("(hp=", String.valueOf(enemy.getHp()), " - ( ", String.valueOf(damage), " - ", String.valueOf(enemy.getDefence()), " ) = ");

            damage -= player.getDefense();
            System.out.println("en att = " + damage);
            if (damage > 0) {
                player.setHitPoints(player.getHitPoints() - damage);
                System.out.println("you hp = " + player.getHitPoints());
            }
        }

    }


    public boolean isAlive(Person person) {
        return (person.getHitPoints() > 0);
    }

    public Person getPlayer() {
        return player;
    }

    public boolean check(Point shift) {

        return true;
    }
}
