package ru.ZIschool.ljalikak;

public interface CommonDAO {
    public void create(Person person);
    public boolean check(String login, String password);
    public void update(Person person);

}
