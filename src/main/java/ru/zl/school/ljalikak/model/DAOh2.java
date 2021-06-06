package ru.zl.school.ljalikak.model;

import ru.zl.school.ljalikak.view.errors.StartException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.Set;

public class DAOh2 implements IDAO {
    private static DAOh2 daOh2;
    private static final String driver = "org.h2.Driver";
    private static final String url = "jdbc:h2:" + System.getenv("PWD") + "/db/Preservations;MV_STORE=false";
    private static final String username = "LJ";
    private static final String password = "123456";
    private Connection connection;

    static final String READ_PERSON = "SELECT PERSON FROM PERSONS WHERE LOGIN = ?";
    static final String WRITE_PERSON = "INSERT INTO PERSONS(LOGIN, PERSON) VALUES (?, ?)";
    static final String UPDATE_PERSON = "UPDATE PERSONS SET PERSON = ? WHERE LOGIN = ?";
    static final String CHECK_LOGIN_USES = "SELECT * FROM PERSONS WHERE LOGIN = ?";
    static final String CHECK_EXIST_PERSON = "SELECT * FROM PERSONS WHERE LOGIN = ?";
    
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
//        dropTable();
//        createTable();
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
                    "PERSON BLOB);"
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean write(Person person, String mode) {
        try (PreparedStatement pstmt = connection.prepareStatement(WRITE_PERSON)) {
            validate(person);
            if (checkLogin(person.getLogin(), mode)) return false;
            pstmt.setString(1, person.getLogin());
            pstmt.setObject(2, person);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    private boolean checkLogin(String login, String mode) {
        try (PreparedStatement pstmt = connection.prepareStatement(CHECK_LOGIN_USES)) {
            pstmt.setString(1, login);
            if (pstmt.executeQuery().first() && mode.equals("gui")) {
                throw new StartException("Игрок с таким логином уже существует!");
            } else if (pstmt.executeQuery().first() && mode.equals("console")) {
                System.out.println("Игрок с таким логином уже существует!");
                return true;
            }
        } catch (StartException e) {
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private boolean checkExist(String login, String mode) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(CHECK_EXIST_PERSON)) {
            pstmt.setString(1, login);
            if (!pstmt.executeQuery().first() && mode.equals("gui")) {
                throw new StartException("Игрока с таким логином не существует!");
            } else if (!pstmt.executeQuery().first() && mode.equals("console")) {
                System.out.println("Игрока с таким логином не существует!");
                return true;
            }
        } catch (StartException e) {
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Person read(String login, String mode) {
        Object object = null;
        try (PreparedStatement pstmt = connection.prepareStatement(READ_PERSON)) {
            if (checkExist(login, mode)) return null;

            pstmt.setString(1, login);

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

    public void validate(Person person) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        if (!violations.isEmpty()) {
            StringBuilder log = new StringBuilder();
            for (ConstraintViolation<Person> errors : violations) {
                log.append(errors.getMessage()).append("\n");
            }
            throw new RuntimeException(log.toString());
        }
    }

    public void closeDB() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
