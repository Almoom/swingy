package ru.zl.school.ljalikak.model;

import ru.zl.school.ljalikak.view.DeadException;

import javax.swing.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Random;

public class Person extends PlaceHolder implements Serializable {
    public static final String NAME = "name";
    public static final String HP = "hp";
    public static final String ATTACK = "attack";
    public static final String DEFENSE = "defense";
    public static final String LEVEL = "level";
    public static final String EXP = "exp";
    private static final long serialVersionUID = 1113799434508676095L;

    @Pattern(regexp = "[0-9a-zA-Z]+")
    private String login;
    @NotNull(message = "Race cannot be null")
    private Types race;
    @Min(value = 1, message = "Level should not be less than 1")
    private int level;
    @Min(value = 0, message = "Level should not be less than 1")
    private int experience;
    @Min(value = 1, message = "Parameter attack should not be less than 1")
    private int attack;
    @Min(value = 1, message = "Parameter defense should not be less than 1")
    private int defense;
    @Min(value = 1, message = "Parameter HP should not be less than 1")
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

    public Person(String login, Types race) {
        super(Types.PLAYER);
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

    public Person(String login, Types race, int level, int experience, int attack, int defense, int hitPoints) {
        super(Types.PLAYER);
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

    public void fight(Person person, String mode) {
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
                    print("атаку", mode);
                    break;
                case 1:
                    this.defense += 1;
                    print("защиту", mode);
                    break;
                case 3:
                    this.hitPoints += 1;
                    print("жизненную силу", mode);
                    break;
            }
        } else {
            throw new DeadException();
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
            System.out.println("you att = " + damage); //todo
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

    public void print(String msg, String mode) {
        if (mode.equals("gui")) {
            JOptionPane.showMessageDialog(null,
                    "Вы получили артефакт, повышающий вашу " + msg + " на 1!",
                    "Artefact!",
                    JOptionPane.PLAIN_MESSAGE);
        } else if (mode.equals("console")) {
            System.out.println("Вы получили артефакт, повышающий вашу " + msg + " на 1!");
        }
    }
}
