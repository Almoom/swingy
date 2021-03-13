package ru.ZIschool.ljalikak.services;

import ru.ZIschool.ljalikak.Person;
import ru.ZIschool.ljalikak.Race;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.*;

public class DAOh2 implements IDAO {
    private static DAOh2 daOh2;
    private static final String driver = "org.h2.Driver";
    private static final String url = "jdbc:h2:" + System.getenv("PWD") + "/db/Preservations;MV_STORE=false";
    private static final String username = "LJ";
    private static final String password = "123456";
    private Connection connection;

    static final String READ_PERSON = "SELECT PERSON FROM PERSONS WHERE LOGIN = ? AND PASSWORD = ?";
    static final String WRITE_PERSON = "INSERT INTO PERSONS(LOGIN, PASSWORD, PERSON) VALUES (?, ?, ?)";
    static final String UPDATE_PERSON = "UPDATE PERSONS SET PERSON = ? WHERE LOGIN = ?";
    static final String CHECK_LOGIN_USES = "SELECT * FROM PERSONS WHERE LOGIN = ?";
    static final String CHECK_EXIST_PERSON = "SELECT * FROM PERSONS WHERE LOGIN = ? AND PASSWORD = ?";
    
    public static DAOh2 getProvider() {
        try {
            if (daOh2 == null) {
                daOh2 = new DAOh2();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return daOh2;
    }
    
    private DAOh2() throws SQLException, ClassNotFoundException {
        createConnection();
        dropTable();
        createTable();

        write(new Person("name1", "1111", Race.HUMAN));
        write(new Person("name2", "1111", Race.MUTANT));
        write(new Person("name3", "1111", Race.GHOUL));

        update(new Person("name3", "1111", Race.GHOUL, 100, 100, 100, 100, 100));
    }

    public synchronized void createConnection() {
        try {
            if (connection == null) {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS PERSONS;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS PERSONS (" +
                    "ID INTEGER PRIMARY KEY auto_increment, " +
                    "LOGIN VARCHAR(128), " +
                    "PASSWORD VARCHAR(128), " +
                    "PERSON BLOB);"
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void write(Person person) {
        try (PreparedStatement pstmt = connection.prepareStatement(WRITE_PERSON)) {
            checkLogin(person.getLogin());
            pstmt.setString(1, person.getLogin());
            pstmt.setString(2, person.getPassword());
            pstmt.setObject(3, person);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void checkLogin(String login) {
        try (PreparedStatement pstmt = connection.prepareStatement(CHECK_LOGIN_USES)) {
            pstmt.setString(1, login);
            if (pstmt.executeQuery().first()) {
                throw new RuntimeException("Login is exist!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Person read(String login, String password) {
        Object object = null;
        try (PreparedStatement pstmt = connection.prepareStatement(READ_PERSON)) {
            checkExist(login, password);

            pstmt.setString(1, login);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            InputStream binaryStream = rs.getBlob(1).getBinaryStream();
            rs.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(binaryStream);
            object = objectInputStream.readObject();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return (Person) object;
    }

    private void checkExist(String login, String password) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(CHECK_EXIST_PERSON)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            if (!pstmt.executeQuery().first()) {
                throw new RuntimeException("Login or password is incorrect!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Person person) {
        try (PreparedStatement pstmt = connection.prepareStatement(UPDATE_PERSON)) {
            pstmt.setObject(1, person);
            pstmt.setString(2, person.getLogin());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
