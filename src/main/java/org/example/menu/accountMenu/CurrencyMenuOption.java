package org.example.menu.accountMenu;

import org.example.entity.Currency;
import org.example.menu.MenuOption;
import org.example.menu.MenuOptionManager;
import org.example.menu.MenuSelect;
import org.example.menu.submitMenu.SubmitMenuOption;

import java.util.ArrayList;
import java.util.List;

public class CurrencyMenuOption extends MenuOption {
    protected List<MenuSelect> currencyMenu = new ArrayList<>();
    private Currency selectedCurrency;

    public CurrencyMenuOption(MenuOption root) {
        super(root, MenuOptionManager.CREATE_ACCOUNT_CURRENCY);
        SubmitMenuOption submitMenuOption = new SubmitMenuOption(this);
        currencyMenu.add(0, Currency.RUBLE);
        currencyMenu.add(1, Currency.DOLLAR);
        currencyMenu.add(2, Currency.EURO);
    }

    public Currency getSelectedCurrency() {
        return selectedCurrency;
    }

    @Override
    public void process(MenuOption menuOption) {
        printMenu();
        int menuPosition = 0;
        String selectNumberInString = scan.next();
        this.selectOption(selectNumberInString);
        try {
            menuPosition = Integer.parseInt(selectNumberInString);
        } catch (NumberFormatException e){
            System.out.println("Неверный ввод");
        }
        selectedCurrency = (Currency) menuMap.get(menuPosition);
    }

    @Override
    public void printMenu() {
        printOptions(currencyMenu);
    }
}