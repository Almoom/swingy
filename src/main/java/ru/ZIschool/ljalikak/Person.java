package ru.ZIschool.ljalikak;

public class Person {
    private String login;
    private String password;
    private String race;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;

    public Person(String login, String password, String race) {
        this.login = login;
        this.password = password;
        this.race = race;
        this.level = 1;
        this.experience = 0;
        this.attack = 1;
        this.defense = 1;
        this.hitPoints = 100;
    }
}
