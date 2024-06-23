package org.example.menu;

public class BackMenuOption extends MenuOption {

    public BackMenuOption(MenuOption root, String title) {
        super(root, title);
    }

    @Override
    public void process(MenuOption menuOption) {
        MenuOption root = menuOption.getRoot();
        if (root != null) {
            root.process(getRoot());
        } else {
            System.out.println("Выход из приложения.");
        }
    }
}