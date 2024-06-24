package org.example.menu.transactionMenu;

import org.example.entity.Account;
import org.example.entity.Client;
import org.example.entity.Currency;
import org.example.menu.ChangeActionMenuOption;
import org.example.menu.MenuOption;
import org.example.menu.MenuOptionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferFundFromToMenuOption extends ChangeActionMenuOption {
    private Map<Long, List<Account>> accountMap;
    private long senderPhoneNumber;
    private Account senderAccount;
    private long recipientPhoneNumber;
    private Account recipientAccount;
    private long sumToSend;

    public TransferFundFromToMenuOption(MenuOption root) {
        super(root, MenuOptionManager.TRANSfER_FUNDS_FROM_TO);
    }

    @Override
    public void process(MenuOption menuOption) {
        interaction();
        boolean actionDone = action();
        if (actionDone) {
            String selectNumberInString = scan.next();
            int selectNumber = Integer.parseInt(selectNumberInString);
            if (menuMap.containsKey(selectNumber)) {
                if (menuMap.get(selectNumber) instanceof Account) {
                    senderAccount = (Account) menuMap.get(selectNumber);
                }
            }
            interactionTwo();
            actionDone = actionTwo();
            if (actionDone) {
                selectNumberInString = scan.next();
                selectNumber = Integer.parseInt(selectNumberInString);
                if (menuMap.containsKey(selectNumber)) {
                    if (menuMap.get(selectNumber) instanceof Account) {
                        recipientAccount = (Account) menuMap.get(selectNumber);
                    }
                }
                startTransaction();
            } else {
                menuOption.getRoot().process(menuOption.getRoot());
            }
            this.selectOption(selectNumberInString);
            super.process(menuOption.getRoot());
        } else {
            menuOption.getRoot().process(menuOption.getRoot());
        }
    }

    private void startTransaction() {
        if (senderAccount.getCurrency().equals(recipientAccount.getCurrency())) {
            if (senderAccount.getBalance() >= sumToSend) {
                senderAccount.setBalance(senderAccount.getBalance() - sumToSend);
                recipientAccount.setBalance(recipientAccount.getBalance() + sumToSend);
                try {
                    connection.setAutoCommit(false);
                    statement.executeUpdate("UPDATE ACCOUNT SET balance = " + senderAccount.getBalance() +
                            "where NUMBER = " + senderAccount.getNumber() + ";");
                    statement.executeUpdate("UPDATE ACCOUNT SET balance = " + recipientAccount.getBalance() +
                            "where NUMBER = " + recipientAccount.getNumber() + ";");
                    connection.commit();
                    System.out.println(MenuOptionManager.TRANSACTION_SUCCESS);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println(MenuOptionManager.NOT_HAVE_BALANCE);
            }
        } else {
            System.out.println(MenuOptionManager.CURRENCY_NOT_EQUALS);
        }
    }

    @Override
    public void interaction() {
        System.out.println(getTitle());
        System.out.println(MenuOptionManager.CLIENT_PHONE_NUMBER_FROM);
        String phoneNumberInString = scan.next();
        senderPhoneNumber = Long.parseLong(phoneNumberInString);
        System.out.println(MenuOptionManager.SEND_SUM);
        String sumToSendInString = scan.next();
        sumToSend = Long.parseLong(sumToSendInString);
    }

    private void interactionTwo() {
        System.out.println(getTitle());
        System.out.println(MenuOptionManager.CLIENT_PHONE_NUMBER_TO);
        String phoneNumberInString = scan.next();
        recipientPhoneNumber = Long.parseLong(phoneNumberInString);
    }

    private boolean actionTwo() {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Client c " +
                    "inner join ACCOUNT a ON c.PHONE_NUMBER = a.CLIENT_PHONE_NUMBER " +
                    "where a.ACTIVE = TRUE AND c.PHONE_NUMBER = " + recipientPhoneNumber);
            accountMap = new HashMap<>();
            mappingEntity(resultSet);
            if (accountMap.isEmpty()) {
                System.out.println(MenuOptionManager.CLIENTS_NO_FOUND);
                return false;
            }
            printOptions(accountMap.get(recipientPhoneNumber));
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean action() {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Client c " +
                    "inner join ACCOUNT a ON c.PHONE_NUMBER = a.CLIENT_PHONE_NUMBER " +
                    "where a.ACTIVE = TRUE AND c.PHONE_NUMBER = " + senderPhoneNumber);
            accountMap = new HashMap<>();
            mappingEntity(resultSet);
            if (accountMap.isEmpty()) {
                System.out.println(MenuOptionManager.CLIENTS_NO_FOUND);
                return false;
            }
            printOptions(accountMap.get(senderPhoneNumber));
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void mappingEntity(ResultSet resultSet) throws SQLException {
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