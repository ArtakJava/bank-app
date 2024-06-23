package org.example;

import org.example.menu.ClientMenu.CreateClientMenuOption;
import org.example.menu.ClientMenu.ShowAllClientMenuOption;
import org.example.menu.MainMenuOption;
import org.example.menu.mainMenu.AccountMenuOption;
import org.example.menu.mainMenu.ClientMenuOption;
import org.example.menu.mainMenu.TransactionMenuOption;

public class Application {

    public static void main(String[] args) {
        MainMenuOption mainMenu = new MainMenuOption(null);
        ClientMenuOption clientMenu = new ClientMenuOption(mainMenu);
        CreateClientMenuOption createClientMenu = new CreateClientMenuOption(clientMenu);
        clientMenu.addMenuOption(1, createClientMenu);
        AccountMenuOption accountMenu = new AccountMenuOption(mainMenu);
        ShowAllClientMenuOption showAllClientMenuForAccount = new ShowAllClientMenuOption(accountMenu);
        accountMenu.addMenuOption(1, showAllClientMenuForAccount);
        TransactionMenuOption transactionMenu = new TransactionMenuOption(mainMenu);
        mainMenu.addMenuOption(1, clientMenu);
        mainMenu.addMenuOption(2, accountMenu);
        mainMenu.addMenuOption(3, transactionMenu);
        mainMenu.process(mainMenu);
    }
}
