package ru.zl.school.ljalikak;

import ru.zl.school.ljalikak.view.PlaceHolder;

import java.io.Serializable;

public class Person extends PlaceHolder implements Serializable {
    private String login;
    private String password;
    private Race race;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;

    public Person(String login, String password, Race Race) {
        super(Types.PlAYER);
        this.login = login;
        this.password = password;
        this.race = Race;
        this.level = 1;
        this.experience = 0;
        this.attack = 1;
        this.defense = 1;
        this.hitPoints = 100;
    }

    public Person(String login, String password, Race Race, int level, int experience, int attack, int defense, int hitPoints) {
        super(Types.PlAYER);
        this.login = login;
        this.password = password;
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

    public String getPassword() {
        return password;
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
        return login + '\t' + password + '\t' + race + '\t'
                + level + '\t' + experience + '\t' + attack + '\t'
                + defense + '\t' + hitPoints;
    }
}
