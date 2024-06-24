package org.example.menu.accountMenu;

import org.example.entity.Account;
import org.example.entity.Currency;
import org.example.menu.MenuOption;
import org.example.menu.submitMenu.SubmitActionMenuOption;

import java.sql.SQLException;

public class ClientSelectForCloseAccountOptionMenu extends SubmitActionMenuOption {
    private Account newAccount;
    private long clientPhoneNumber;

    public ClientSelectForCloseAccountOptionMenu(MenuOption root, String title, long clientPhoneNumber) {
        super(root, title);
        this.clientPhoneNumber = clientPhoneNumber;
    }

    @Override
    public void process(MenuOption menuOption) {
        submitInteraction();
        String selectNumberInString = scan.next();
        this.selectOption(selectNumberInString);
        menuOption.getRoot().process(menuOption.getRoot());
    }

    @Override
    public void interaction() {
    }

    @Override
    public boolean action() {
        long number = newAccount.getNumber();
        long balance = newAccount.getBalance();
        boolean active = newAccount.isActive();
        long bik = newAccount.getBik();
        Currency currency = newAccount.getCurrency();
        try {
            statement.execute("INSERT INTO ACCOUNT VALUES(" +
                    "'" + number + "'," +
                    "'" + balance + "'," +
                    "'" + active + "'," +
                    "" + bik + "," +
                    "'" + currency.toString() + "'," +
                    "" + clientPhoneNumber + ");");
            System.out.println("Счет " + newAccount + " успешно создан!");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public long getClientPhoneNumber() {
        return clientPhoneNumber;
    }
}