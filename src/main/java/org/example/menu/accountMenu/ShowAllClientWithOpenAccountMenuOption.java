package org.example.menu.accountMenu;

import org.example.entity.Account;
import org.example.entity.Client;
import org.example.entity.Currency;
import org.example.menu.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowAllClientWithOpenAccountMenuOption extends ActionMenuOption {
    private Map<Long, ClientSelectForCloseAccountOptionMenu> clientMap;
    private Map<Long, List<Account>> accountMap;

    public ShowAllClientWithOpenAccountMenuOption(MenuOption root) {
        super(root, MenuOptionManager.SHOW_ALL_CLIENTS_WITH_OPEN_ACCOUNTS);
    }

    @Override
    public void process(MenuOption menuOption) {
        boolean actionDone = action();
        if (actionDone) {
            String selectNumberInString = scan.next();
            int selectNumber = Integer.parseInt(selectNumberInString);
            ClientSelectForCloseAccountOptionMenu client;
            if (menuMap.containsKey(selectNumber)) {
                if (menuMap.get(selectNumber) instanceof ClientSelectForCloseAccountOptionMenu) {
                    client = (ClientSelectForCloseAccountOptionMenu) menuMap.get(selectNumber);
                    printOptions(accountMap.get(client.getClientPhoneNumber()));
                } else if (menuMap.get(selectNumber) instanceof BackMenuOption) {
                    ((BackMenuOption) menuMap.get(selectNumber)).process(((BackMenuOption) menuMap.get(selectNumber)));
                }
            }
            String selectAccountInString = scan.next();
            int accountNumber = Integer.parseInt(selectAccountInString);
            if (menuMap.get(accountNumber) instanceof Account) {
                Account account = (Account) menuMap.get(accountNumber);
                closeAccount(account);
            }
            this.selectOption(selectNumberInString);
            super.process(menuOption.getRoot());
        } else {
            menuOption.getRoot().process(menuOption.getRoot());
        }
    }

    private void closeAccount(Account account) {
        try {
            statement.executeUpdate("UPDATE ACCOUNT SET active = 'false' where NUMBER = " + account.getNumber() + ";");
            System.out.println("Счет " + account + " закрыт.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean action() {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Client c " +
                    "inner join ACCOUNT a ON c.PHONE_NUMBER = a.CLIENT_PHONE_NUMBER where a.ACTIVE = TRUE");
            mappingEntity(resultSet);
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

    private void mappingEntity(ResultSet resultSet) throws SQLException {
        clientMap = new HashMap<>();
        accountMap = new HashMap<>();
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
            long number = Long.parseLong(resultSet.getString("NUMBER"));
            long balance = Long.parseLong(resultSet.getString("BALANCE"));
            boolean active = Boolean.parseBoolean(resultSet.getString("ACTIVE"));
            long bik = Long.parseLong(resultSet.getString("BIK"));
            Currency currency = Currency.valueOf(resultSet.getString("CURRENCY"));
            Account account = new Account(
                    number,
                    balance,
                    active,
                    bik,
                    currency
            );
            ClientSelectForCloseAccountOptionMenu clientSelectOptionMenu =
                    new ClientSelectForCloseAccountOptionMenu(this, result.toString(), phoneNumberForCreation);
            clientMap.put(result.getPhoneNumber(), clientSelectOptionMenu);
            List<Account> oldList;
            if (accountMap.containsKey(result.getPhoneNumber())) {
                oldList = accountMap.get(result.getPhoneNumber());
            } else {
                oldList = new ArrayList<>();
            }
            oldList.add(account);
            accountMap.put(result.getPhoneNumber(), oldList);
        }
    }
}