package org.example.menu;

import org.example.menu.submitMenu.ChangeMenuOption;
import org.example.menu.submitMenu.SubmitActionMenuOption;

public abstract class ChangeActionMenuOption extends SubmitActionMenuOption {

    public ChangeActionMenuOption(MenuOption root, String title) {
        super(root, title);
        ChangeMenuOption changeMenuOption = new ChangeMenuOption(this);
        submitMenu.add(1, changeMenuOption);
    }
}