package org.example.menu.accountMenu;

import org.example.entity.Account;
import org.example.entity.Currency;
import org.example.menu.MenuOption;
import org.example.menu.MenuOptionManager;
import org.example.menu.submitMenu.SubmitActionMenuOption;

import java.sql.SQLException;

public class ClientSelectForOpenAccountOptionMenu extends SubmitActionMenuOption {
    private Account newAccount;
    private long clientPhoneNumber;

    public ClientSelectForOpenAccountOptionMenu(MenuOption root, String title, long clientPhoneNumber) {
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
        System.out.println(getTitle());
        System.out.println(MenuOptionManager.CREATE_ACCOUNT_NUMBER);
        String numberInString = scan.next();
        long number = Long.parseLong(numberInString);
        System.out.println(MenuOptionManager.CREATE_ACCOUNT_BIK);
        String bikInString = scan.next();
        long bik = Long.parseLong(bikInString);
        CurrencyMenuOption currencyMenuOption = new CurrencyMenuOption(this);
        currencyMenuOption.process(currencyMenuOption);
        newAccount = new Account(number, bik, currencyMenuOption.getSelectedCurrency());
        System.out.println(newAccount);
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
}