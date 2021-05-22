package ru.zl.school.ljalikak;

import ru.zl.school.ljalikak.view.PlaceHolder;

import java.io.Serializable;

public class Person extends PlaceHolder implements Serializable {
    private String login;
    private Race race;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;

    public Person(String login, Race race) {
        super(Types.PlAYER);
//        if (login.isEmpty())
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
        this.experience = lvlCalc(level - 1);
        this.attack = level * 2;
        this.defense = level * 2;
        this.hitPoints = level * 2;
    }

    public Person(String login, Race Race, int level, int experience, int attack, int defense, int hitPoints) {
        super(Types.PlAYER);
        this.login = login;
        this.race = Race;
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

    @Override
    public String toString() {
        return login + '\t' + race + '\t'
                + level + '\t' + experience + '\t' + attack + '\t'
                + defense + '\t' + hitPoints;
    }

    private int lvlCalc(int level) {
        return level * 1000 + (level - 1) * (level - 1) * 450;
    }
}
