package ru.zl.school.ljalikak.model;

import ru.zl.school.ljalikak.view.PlaceHolder;
import ru.zl.school.ljalikak.view.Warrior;

import java.awt.*;
import java.util.ArrayList;
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
        width = size;
        height = size;
        map = new Place[height][width];
        initMap();
        player.setXY(size/2,size/2);
        this.player = player;

        insertOnMap(player);

        logger = new StringBuilder("");
    }

    private int getSize(int level) {
        return (level - 1) * 5 + 10 - (level % 2);
    }

    public void initMap() {
        random = new Random();
        animals = new ArrayList<>();
//        map = new Place[SIZE][SIZE];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = new Place(EMPTY);
                if (i == height / 2 || j == width / 2)
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
        if (y >= 0 && y < width && x >= 0 && x < height) {
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

    public void tryMovePerson(Point shift) {
        Place place = getPlace(shift.x + player.getX(), shift.y + player.getY());
        if (place != OUT && place.getObject() == EMPTY) {
            insertOnMap(EMPTY, player.getX(), player.getY());
            player.setXY(shift.x + player.getX(), shift.y + player.getY());
            insertOnMap(player);
        } else if (place.getObject().getTypes() == Types.ANIMAL) {
            enemy = new Person("rat", Race.HUMAN, 1, 300, 1, 1, 5);
            enemy.setXY(shift.x + player.getX(), shift.y + player.getY());
            player.fight(enemy);
            insertOnMap(EMPTY, player.getX(), player.getY());
            player.setXY(enemy.getX(), enemy.getY());
            insertOnMap(player, enemy.getX(), enemy.getY());
        }
    }

    public Person getPlayer() {
        return player;
    }

}
