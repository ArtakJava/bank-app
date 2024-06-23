package org.example.menu.submitMenu;

import org.example.menu.MenuOption;
import org.example.menu.MenuOptionManager;

public class ChangeMenuOption extends MenuOption {

    public ChangeMenuOption(MenuOption root) {
        super(root, MenuOptionManager.CHANGE_CLIENT_INFORMATION);
    }
}