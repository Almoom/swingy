package ru.zl.school.ljalikak.model;

import ru.zl.school.ljalikak.view.PlaceHolder;

import java.io.Serializable;
import java.util.Random;

public class Person extends PlaceHolder implements Serializable {
    public static final String NAME = "name";
    public static final String HP = "hp";
    public static final String ATTACK = "attack";
    public static final String DEFENSE = "defense";
    public static final String HIT_POINTS = "hit_points";
    public static final String LEVEL = "level";
    public static final String EXP = "exp";
    public static final String TYPE = "type";

    private String login;
    private Race race;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;
    private int expNextLevel;
    private Person enemy;

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getExpNextLevel() {
        return expNextLevel;
    }

    public void setExperience(int experience) {
        this.experience = experience;
        if (experience > expNextLevel) {
            setLevel(level+1);
        }
    }

    public Person(String login, Race race) {
        super(Types.PlAYER);
        this.login = login;
        this.race = race;
        switch (race) {
            case HUMAN: {
                this.level = 1;
            } break;
            case GHOUL: {
                this.level = 2;
            } break;
            case MUTANT: {
                this.level = 3;
            } break;
        }
        this.experience = 0;
        this.attack = level;
        this.defense = level;
        this.hitPoints = 10 + level;
        setLevel(level);
    }

    public Person(String login, Race race, int level, int experience, int attack, int defense, int hitPoints) {
        super(Types.PlAYER);
        this.login = login;
        this.race = race;
        this.level = level;
        this.experience = experience;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        setLevel(level);
    }

    public String getLogin() {
        return login;
    }

    public Race getRace() {
        return race;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setLevel(int level) {
        this.level = level;
        expCalc();

    }

    public void fight(Person person) {
        this.enemy = person;
//        enemyLogger = enemy.logger;
//        enemy.enemyLogger = logger;
//        clearLogger();
        while (isAlive(this) && isAlive(enemy)) {
            attack(true);
            if (isAlive(enemy))
                attack(false);
        }

        if (isAlive(this)) {
//            log(name, " kill ", enemy.getName(), "!!!\n");
            this.setExperience(this.getExperience() + enemy.getExperience());
//            winner = this;
//            insertOnMap(EMPTY, this.getX(), this.getY());
////            Warrior winner = warrior.fight(enemy);
//            this.setXY(enemy.getX(), enemy.getY());
//            insertOnMap(this, enemy.getX(), enemy.getY());

//            insertOnMap(player);
//            getPlace(player.getX(), player.getY()).setType(Types.BLOOD);
        } else {
            throw new DeadException("dgh");
//            log(enemy.getName(), " kill ", name, "!!!\n");
//            enemy.addExperience(getExperience());
//            winner = enemy;
        }
//        enemy.enemyLogger = null;
//        enemyLogger = null;
    }

    public int attack(int attack) {
        Random random = new Random();
        return (random.nextInt(attack + 2));
    }

    private void attack(boolean im) {
        if (im) {
            int damage = attack(this.getAttack());

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

            damage -= this.getDefense();
            System.out.println("en att = " + damage);
            if (damage > 0) {
                this.setHitPoints(this.getHitPoints() - damage);
                System.out.println("you hp = " + this.getHitPoints());
            }
        }

    }


    public boolean isAlive(Person person) {
        return (person.getHitPoints() > 0);
    }

    @Override
    public String toString() {
        return login + '\t' + race + '\t'
                + level + '\t' + experience + '\t' + attack + '\t'
                + defense + '\t' + hitPoints;
    }

    private void expCalc() {
        expNextLevel = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
    }
}
