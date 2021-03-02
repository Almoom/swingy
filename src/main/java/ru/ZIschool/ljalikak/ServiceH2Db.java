package ru.ZIschool.ljalikak;

import java.sql.*;

public class ServiceH2Db implements CommonDAO {

    private static final String url = "jdbc:h2:/Users/ljalikak/IdeaProjects/swingy/db/Preservations;MV_STORE=false";
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
        statement.execute("DROP TABLE PERSONS;");
    }

    public void createTable() throws SQLException
    {
        statement.execute("CREATE TABLE if not exists 'PERSONS' (" +
                "'ID' INTEGER PRIMARY KEY auto_increment, " +
                "'LOGIN' VARCHAR(25), " +
                "'PASSWORD' VARCHAR(25), " +
                "'RACE' VARCHAR(10), " +
                "'LEVEL' INT, " +
                "'EXP' INT, " +
                "'ATTACK' INT, " +
                "'DEFENSE' INT, " +
                "'HP' INT);"
        );
        System.out.println("Table PERSONS was created if not exists");
    }

    public void init() throws SQLException
    {
        Person person = new Person("Name1", "Pass1", Types.HUMAN);
        create(person);

        person = new Person("Name2", "Pass2", Types.MUTANT);
        create(person);

        person = new Person("Name3", "Pass3", Types.GHOUL);
        create(person);

        System.out.println("Table was init");
    }

    public void readAll() throws SQLException {
        resSet = statement.executeQuery("SELECT * FROM PERSONS");
        while (resSet.next()) {
            int id = resSet.getInt("id");
            String login = resSet.getString("login");
            String password = resSet.getString("password");
            int exp = resSet.getInt("exp");
            int hp = resSet.getInt("hp");
            int maxHp = resSet.getInt("maxHp");
            int level = resSet.getInt("level");
            System.out.printf("ID = %d, login = %s, pas = %s, exp = %d, hp = %d, maxHp = %d, level = %d\n",
                    id, login, password, exp, hp, maxHp, level);
        }
    }

    @Override
    public void create(Person person) {

    }

    @Override
    public boolean check(String login, String password) {
        return false;
    }

    @Override
    public void update(Person person) {

    }

}
