package org.example.menu.submitMenu;

import org.example.menu.ActionMenuOption;
import org.example.menu.MenuOption;
import org.example.menu.MenuSelect;

import java.util.ArrayList;
import java.util.List;

public abstract class SubmitActionMenuOption extends ActionMenuOption {
    protected List<MenuSelect> submitMenu = new ArrayList<>();

    public SubmitActionMenuOption(MenuOption root, String title) {
        super(root, title);
        SubmitMenuOption submitMenuOption = new SubmitMenuOption(this);
        submitMenu.add(0, submitMenuOption);
    }
    
    public void submitInteraction() {
        interaction();
        printOptions(submitMenu);
    }
    
    public abstract void interaction();
}