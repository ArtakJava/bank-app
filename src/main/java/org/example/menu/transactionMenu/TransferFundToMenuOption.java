package org.example.menu.transactionMenu;

import org.example.entity.Client;
import org.example.menu.ChangeActionMenuOption;
import org.example.menu.MenuOption;
import org.example.menu.MenuOptionManager;

import java.sql.SQLException;

public class TransferFundToMenuOption extends ChangeActionMenuOption {
    private Client newClient;

    public TransferFundToMenuOption(MenuOption root) {
        super(root, MenuOptionManager.TRANSFER_FUNDS_TO);
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
        int phoneNumber = scan.nextInt();
        System.out.println(MenuOptionManager.CREATE_CLIENT_INN);
        String inn = scan.next();
        System.out.println(MenuOptionManager.CREATE_CLIENT_ADDRESS);
        String address = scan.next();
        newClient = new Client(lastName, firstName, middleName, phoneNumber, inn, address);
        System.out.println(newClient);
    }

    @Override
    public boolean action() throws SQLException {
        return false;
    }
}