package org.example.menu.mainMenu;

import org.example.menu.MenuOption;
import org.example.menu.MenuOptionManager;

public class ClientMenuOption extends MenuOption {

    public ClientMenuOption(MenuOption root) {
        super(root, MenuOptionManager.CLIENT_MENU);
    }
}