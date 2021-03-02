package ru.ZIschool.ljalikak;

public class Person {
    private String login;
    private String password;
    private Types race;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;

    public Person(String login, String password, Types race) {
        this.login = login;
        this.password = password;
        this.race = race;
        this.level = 1;
        this.experience = 0;
        this.attack = 1;
        this.defense = 1;
        this.hitPoints = 100;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Types getRace() {
        return race;
    }

    public void setRace(Types race) {
        this.race = race;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
