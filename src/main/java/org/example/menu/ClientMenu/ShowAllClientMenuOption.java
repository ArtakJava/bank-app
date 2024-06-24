package org.example.menu.ClientMenu;

import org.example.entity.Client;
import org.example.menu.ActionMenuOption;
import org.example.menu.ClientSelectForOpenAccountOptionMenu;
import org.example.menu.MenuOption;
import org.example.menu.MenuOptionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ShowAllClientMenuOption extends ActionMenuOption {
    private Map<Long, ClientSelectForOpenAccountOptionMenu> clientMap;

    public ShowAllClientMenuOption(MenuOption root) {
        super(root, MenuOptionManager.SHOW_ALL_CLIENTS);
    }

    @Override
    public void process(MenuOption menuOption) {
        boolean actionDone = action();
        if (actionDone) {
            String selectNumberInString = scan.next();
            this.selectOption(selectNumberInString);
            super.process(menuOption.getRoot());
        } else {
            menuOption.getRoot().process(menuOption.getRoot());
        }
    }

    @Override
    public boolean action() {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Client");
            clientMap = new HashMap<>();
            while (resultSet.next()) {
                String lastNameForCreation = resultSet.getString("LAST_NAME");
                String firstNameForCreation = resultSet.getString("FIRST_NAME");
                String middleNameForCreation = resultSet.getString("MIDDLE_NAME");
                long phoneNumberForCreation = resultSet.getLong("PHONE_NUMBER");
                String innForCreation = resultSet.getString("INN");
                String addressForCreation = resultSet.getString("ADDRESS");
                Client result = new Client(
                        lastNameForCreation,
                        firstNameForCreation,
                        middleNameForCreation,
                        phoneNumberForCreation,
                        innForCreation,
                        addressForCreation
                        );
                ClientSelectForOpenAccountOptionMenu clientSelectOptionMenu =
                        new ClientSelectForOpenAccountOptionMenu(this, result.toString(), phoneNumberForCreation);
                clientMap.put(result.getPhoneNumber(), clientSelectOptionMenu);
            }
            if (clientMap.isEmpty()) {
                System.out.println("Клиентов не найдено!");
                return false;
            }
            printOptions(clientMap.values());
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}