package org.example.menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ActionMenuOption extends MenuOption {
    protected static final Connection connection;
    protected static final Statement statement;

    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:h2:~/bank;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'src/main/resources/init.sql'",
                    "sa",
                    "");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ActionMenuOption(MenuOption root, String title) {
        super(root, title);
    }

    public abstract boolean action() throws SQLException;
}