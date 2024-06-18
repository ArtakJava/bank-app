package org.example;

import java.sql.*;

public class Application {

    public static void main(String[] args) {
        System.out.println("sot");
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:h2:~/bank;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'src/main/resources/init.sql'",
                    "sa",
                    "");
            Statement st = null;
            st = connection.createStatement();
            st.execute("INSERT INTO CLIENT VALUES('89964049449','MATEVOSIAN','ARTAK','ARTUROVICH','146956565','UFA')");
            ResultSet resultSet = st.executeQuery("SELECT * FROM Client");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("PHONE_NUMBER") + ": " + resultSet.getString("FIRST_NAME"));
            }
            resultSet.close();
            st.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
