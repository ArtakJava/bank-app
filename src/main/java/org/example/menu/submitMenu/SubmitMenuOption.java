package org.example.menu.submitMenu;

import org.example.menu.MenuOption;
import org.example.menu.MenuOptionManager;

import java.sql.SQLException;

public class SubmitMenuOption extends MenuOption {

    public SubmitMenuOption(MenuOption root) {
        super(root, MenuOptionManager.SUBMIT_ACTION);
    }

    @Override
    public void process(MenuOption menuOption) {
        SubmitActionMenuOption menuOptionRoot = (SubmitActionMenuOption) menuOption.getRoot();
        try {
            menuOptionRoot.action();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}