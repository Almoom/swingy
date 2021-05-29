package ru.zl.school.ljalikak;

import ru.zl.school.ljalikak.view.PlaceHolder;

import javax.swing.*;
import java.io.Serializable;

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

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getExpNextLevel() {
        return expNextLevel;
    }

    public void setExperience(int experience) {
        this.experience = experience;
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
                this.level = 5;
            } break;
            case MUTANT: {
                this.level = 10;
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
