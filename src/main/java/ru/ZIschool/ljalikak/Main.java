package ru.ZIschool.ljalikak;

import java.sql.*;

public class Main {

    private static final String url = "jdbc:h2:/Users/ljalikak/IdeaProjects/swingy/db/Preservations;MV_STORE=false";
    private static final String user = "LJ";
    private static final String password = "123456";

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PERSONS;");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
