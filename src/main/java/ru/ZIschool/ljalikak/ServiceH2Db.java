package ru.ZIschool.ljalikak;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceH2Db implements CommonDAO {
    private static final String driver = "org.h2.Driver";
    private static final String url = "jdbc:h2:" + System.getenv("PWD") + "/db/Preservations;MV_STORE=false";
    private static final String username = "LJ";
    private static final String password = "123456";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resSet;
    private StringBuilder request;
    static final String READ_OBJECT_SQL = "SELECT PERSON FROM PERSONS2 WHERE LOGIN = ?";
    static final String WRITE_OBJECT_SQL = "INSERT INTO PERSONS2(LOGIN, PASSWORD, PERSON) VALUES (?, ?, ?)";
    Connection conn = DriverManager.getConnection(url, username, password);

    public ServiceH2Db() throws SQLException, ClassNotFoundException {
        createConnection();
        dropTable();
        createTable();
//        init();
        readAll();
    }
    
    public synchronized void createConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
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
        statement.execute("DROP TABLE if exists PERSONS2;");
    }

    public void createTable() throws SQLException
    {
        statement.execute("CREATE TABLE if not exists PERSONS (" +
                "ID INTEGER PRIMARY KEY auto_increment, " +
                "LOGIN VARCHAR(128), " +
                "PASSWORD VARCHAR(128), " +
                "RACE VARCHAR(10), " +
                "LEVEL INT, " +
                "EXP INT, " +
                "ATTACK INT, " +
                "DEFENSE INT, " +
                "HP INT);"
        );
        statement.execute("CREATE TABLE if not exists PERSONS2 (" +
                "ID INTEGER PRIMARY KEY auto_increment, " +
                "LOGIN VARCHAR(128), " +
                "PASSWORD VARCHAR(128), " +
                "PERSON BLOB);"
        );
        System.out.println("Table PERSONS was created if not exists");
    }

    public void init() throws SQLException
    {
        List<Person> list = new ArrayList<>();



        Person person = new Person("name1", "Pass1", Types.HUMAN);
        create(person);
        writeJavaObject(conn, person);

        person = new Person("name2", "Pass2", Types.MUTANT);
        create(person);
        list.add(person);

        person = new Person("name3", "Pass3", Types.GHOUL);
        create(person);
        list.add(person);


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
        Person person = readJavaObject(conn, "name1");
        System.out.println(person.getHitPoints());
    }

    @Override
    public void create(Person person) {
        try {
            if (checkLogin(person.getLogin())) {
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

    public static long writeJavaObject(Connection conn, Person person) throws SQLException {
        String className = person.getClass().getName();
        PreparedStatement pstmt = conn.prepareStatement(WRITE_OBJECT_SQL);
        // set input parameters
        pstmt.setString(1, person.getLogin());
        pstmt.setString(2, person.getPassword());
        pstmt.setObject(3, person);
        pstmt.executeUpdate();
        // get the generated key for the id
        ResultSet rs = pstmt.getGeneratedKeys();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        pstmt.close();
        System.out.println("writeJavaObject: done serializing: " + className);
        return id;
    }

    public static Person readJavaObject(Connection conn, String name) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(READ_OBJECT_SQL);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Object object = null;
        try {
            InputStream binaryStream = rs.getBlob(1).getBinaryStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(binaryStream);

            object = objectInputStream.readObject();
//        rs.getClob(1).
        } catch (Exception e){
            e.printStackTrace();
        }
        
        String className = object.getClass().getName();
        rs.close();
//        pstmt.close();
        System.out.println("readJavaObject: done de-serializing: " + className);
        return (Person) object;
    }

    private boolean checkLogin(String login) throws SQLException {
        return statement.executeQuery("SELECT * FROM PERSONS WHERE LOGIN = '" + login + "';").first();
    }

    @Override
    public void read(Person person) {
        try {
            if (!check(person.getLogin(), person.getPassword())) {
                throw new RuntimeException("Login or password is incorrect!");
            }
            //todo read 1 рожу
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean check(String login, String password) throws SQLException {
        return statement.executeQuery("SELECT * FROM PERSONS WHERE LOGIN = '" + login + "' AND PASSWORD = '" + password + "';").first();
    }

    @Override
    public void update(Person person) {

    }

}
