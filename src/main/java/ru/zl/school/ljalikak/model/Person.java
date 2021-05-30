package ru.zl.school.ljalikak.model;

import ru.zl.school.ljalikak.view.PlaceHolder;

import javax.swing.*;
import java.io.Serializable;
import java.util.Random;

public class Person extends PlaceHolder implements Serializable {
    public static final String NAME = "name";
    public static final String HP = "hp";
    public static final String ATTACK = "attack";
    public static final String DEFENSE = "defense";
    public static final String LEVEL = "level";
    public static final String EXP = "exp";

    private String login;
    private Race race;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;
    private int expNextLevel;
    private Person enemy;
    private Random random = new Random();

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
        setLevel(level);
    }

    public Person(String login, Race race, int level, int experience, int attack, int defense, int hitPoints) {
        super(Types.PlAYER);
        setLevel(level);
        this.login = login;
        this.race = race;
        this.level = level;
        this.experience = experience;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    public void setLevel(int level) {
        this.level = level;
        this.attack = Math.max(this.attack, level);
        this.defense = Math.max(this.defense, level);
        this.hitPoints = Math.max(this.hitPoints, 2 * level + 5);
        expCalc();

    }

    public String getLogin() {
        return login;
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

    public void fight(Person person) {
        this.enemy = person;
        while (isAlive(this) && isAlive(enemy)) {
            attack(true);
            if (isAlive(enemy))
                attack(false);
        }

        if (isAlive(this)) {
            this.setExperience(this.getExperience() + enemy.getExperience());
            int artefact = random.nextInt(10);
            switch (artefact) {
                case 0:
                    this.attack += 1;
                    print("атаку");
                    break;
                case 1:
                    this.defense += 1;
                    print("защиту");
                    break;
                case 3:
                    this.hitPoints += 1;
                    print("жизненную силу");
                    break;
            }
        } else {
            throw new DeadException("sghsgfh");
        }
    }

    public int attack(int attack) {
        Random random = new Random();
        return (random.nextInt(attack + 2));
    }

    private void attack(boolean im) {
        if (im) {
            int damage = attack(this.getAttack());

            damage -= enemy.getDefense();
            System.out.println("you att = " + damage);
            if (damage > 0) {
                enemy.setHitPoints(enemy.getHitPoints() - damage);
                System.out.println("en hp = " + enemy.getHitPoints());
            }
        } else {
            int damage = attack(enemy.getAttack());

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

    public void print(String msg) {
        JOptionPane.showMessageDialog(null,
                "Вы получили артефакт, повышающий вашу " + msg + " на 1!",
                "Artefact!",
                JOptionPane.PLAIN_MESSAGE);
    }
}
