package org.example.menu;

import org.example.menu.ClientMenu.CreateClientMenuOption;
import org.example.menu.ClientMenu.ShowAllClientMenuOption;
import org.example.menu.accountMenu.ShowAllClientWithOpenAccountMenuOption;
import org.example.menu.mainMenu.AccountMenuOption;
import org.example.menu.mainMenu.ClientMenuOption;
import org.example.menu.mainMenu.TransactionMenuOption;
import org.example.menu.transactionMenu.TransferFundFromToMenuOption;
import org.example.menu.transactionMenu.TransferFundToMenuOption;

public class MainMenuOption extends MenuOption {

    public MainMenuOption(MenuOption root) {
        super(root, MenuOptionManager.MAIN_MENU);
        ClientMenuOption clientMenu = new ClientMenuOption(this);
        CreateClientMenuOption createClientMenu = new CreateClientMenuOption(clientMenu);
        clientMenu.addMenuOption(1, createClientMenu);
        AccountMenuOption accountMenu = new AccountMenuOption(this);
        ShowAllClientMenuOption showAllClientMenuForAccount = new ShowAllClientMenuOption(accountMenu);
        ShowAllClientWithOpenAccountMenuOption showAllClientWithOpenAccountMenuOption =
                new ShowAllClientWithOpenAccountMenuOption(accountMenu);
        accountMenu.addMenuOption(1, showAllClientMenuForAccount);
        accountMenu.addMenuOption(2, showAllClientWithOpenAccountMenuOption);
        TransactionMenuOption transactionMenu = new TransactionMenuOption(this);
        TransferFundToMenuOption transferFundToMenuOption = new TransferFundToMenuOption(transactionMenu);
        TransferFundFromToMenuOption transferFundFromToMenuOption = new TransferFundFromToMenuOption(transactionMenu);
        transactionMenu.addMenuOption(1, transferFundToMenuOption);
        transactionMenu.addMenuOption(2, transferFundFromToMenuOption);
        this.addMenuOption(1, clientMenu);
        this.addMenuOption(2, accountMenu);
        this.addMenuOption(3, transactionMenu);
    }
}