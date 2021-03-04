package ru.ZIschool.ljalikak;

import java.sql.*;

public class ServiceH2Db implements CommonDAO {
    private static final String url = "jdbc:h2:" + System.getenv("PWD") + "/db/Preservations;MV_STORE=false";
    private static final String user = "LJ";
    private static final String password = "123456";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resSet;
    private StringBuilder request;


    public ServiceH2Db() throws SQLException, ClassNotFoundException {
        createConnection();
        dropTable();
        createTable();
        init();
        readAll();
    }
    
    public synchronized void createConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            System.out.println("Connect to database successfully");
        }
    }

    public void closeConnection() throws SQLException
    {
        if (connection != null)
            connection.close();
        if (statement != null)
            statement.close();
        if (resSet != null)
            resSet.close();

        System.out.println("Connect to database remotely");
    }

    public void dropTable() throws SQLException
    {
        statement.execute("DROP TABLE if exists PERSONS;");
    }

    public void createTable() throws SQLException
    {
        statement.execute("CREATE TABLE if not exists PERSONS (" +
                "ID INTEGER PRIMARY KEY auto_increment, " +
                "LOGIN VARCHAR(25), " +
                "PASSWORD VARCHAR(25), " +
                "RACE VARCHAR(10), " +
                "LEVEL INT, " +
                "EXP INT, " +
                "ATTACK INT, " +
                "DEFENSE INT, " +
                "HP INT);"
        );
        System.out.println("Table PERSONS was created if not exists");
    }

    public void init() throws SQLException
    {
        Person person = new Person("name1", "Pass1", Types.HUMAN);
        create(person);

        person = new Person("name2", "Pass2", Types.MUTANT);
        create(person);

        person = new Person("name3", "Pass3", Types.GHOUL);
        create(person);

        System.out.println("Table was init");
    }

    public void readAll() throws SQLException {
        resSet = statement.executeQuery("SELECT * FROM PERSONS");
        System.out.println("ID\tLOGIN\tPASS\tRACE\tLVL\tEXP\tATT\tDEF\tHP");
        while (resSet.next()) {
            int id = resSet.getInt("ID");
            String login = resSet.getString("LOGIN");
            String password = resSet.getString("PASSWORD");
            String race = resSet.getString("RACE");
            int level = resSet.getInt("LEVEL");
            int exp = resSet.getInt("EXP");
            int attack = resSet.getInt("ATTACK");
            int defense = resSet.getInt("DEFENSE");
            int hp = resSet.getInt("HP");

            System.out.printf("%d\t%s\t%s\t%s\t%d\t%d\t%d\t%d\t%d\n",
                    id, login, password, race, level, exp, attack, defense, hp);
        }
        System.out.println();
    }

    @Override
    public void create(Person person) {
        try {
            if (check(person.getLogin())) {
                throw new RuntimeException("Login is exist!");
            }
            statement.execute("INSERT INTO PERSONS (LOGIN, PASSWORD, RACE, LEVEL, EXP, ATTACK, DEFENSE, HP) VALUES ("
                    + "'" + person.getLogin() + "'"
                    + ",'" + person.getPassword() + "'"
                    + ",'" + person.getRace() + "'"
                    + "," + person.getLevel()
                    + "," + person.getExperience()
                    + "," + person.getAttack()
                    + "," + person.getDefense()
                    + "," + person.getHitPoints()
                    + ");");
            readAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void read(Person person) {
        try {
            if (!check(person.getLogin())) {
                throw new RuntimeException("Person is not exist!");
            }
            //todo read 1 рожу
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean check(String login) throws SQLException {
        return statement.executeQuery("SELECT * FROM PERSONS WHERE LOGIN = '" + login + "';").first();
    }

    @Override
    public void update(Person person) {

    }

}
