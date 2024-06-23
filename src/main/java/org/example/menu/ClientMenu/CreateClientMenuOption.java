package org.example.menu.ClientMenu;

import org.example.entity.Client;
import org.example.menu.ChangeActionMenuOption;
import org.example.menu.MenuOption;
import org.example.menu.MenuOptionManager;

import java.sql.SQLException;

public class CreateClientMenuOption extends ChangeActionMenuOption {
    private Client newClient;

    public CreateClientMenuOption(MenuOption root) {
        super(root, MenuOptionManager.CREATE_CLIENT);
    }

    @Override
    public void process(MenuOption menuOption) {
        submitInteraction();
        String selectNumberInString = scan.next();
        this.selectOption(selectNumberInString);
        super.process(menuOption.getRoot());
    }

    @Override
    public void interaction() {
        System.out.println(getTitle());
        System.out.println(MenuOptionManager.CREATE_CLIENT_LAST_NAME);
        String lastName = scan.next();
        System.out.println(MenuOptionManager.CREATE_CLIENT_FIRST_NAME);
        String firstName = scan.next();
        System.out.println(MenuOptionManager.CREATE_CLIENT_MIDDLE_NAME);
        String middleName = scan.next();
        System.out.println(MenuOptionManager.CREATE_CLIENT_PHONE_NUMBER);
        String phoneNumberInString = scan.next();
        long phoneNumber = Long.parseLong(phoneNumberInString);
        System.out.println(MenuOptionManager.CREATE_CLIENT_INN);
        String inn = scan.next();
        System.out.println(MenuOptionManager.CREATE_CLIENT_ADDRESS);
        String address = scan.next();
        newClient = new Client(lastName, firstName, middleName, phoneNumber, inn, address);
        System.out.println(newClient);
    }

    @Override
    public boolean action() {
        String lastNameForCreation = newClient.getLastName();
        String firstNameForCreation = newClient.getFirstName();
        String middleNameForCreation = newClient.getMiddleName();
        long phoneNumberForCreation = newClient.getPhoneNumber();
        String innForCreation = newClient.getInn();
        String addressForCreation = newClient.getAddress();
        try {
            statement.execute("INSERT INTO CLIENT VALUES(" +
                    "'" + lastNameForCreation + "'," +
                    "'" + firstNameForCreation + "'," +
                    "'" + middleNameForCreation + "'," +
                    "" + phoneNumberForCreation + "," +
                    "'" + innForCreation + "'," +
                    "'" + addressForCreation + "');");
            System.out.println("Клиент " + newClient + " успешно создан!");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}