/*
package org.example.menu;

public class MenuFactory {
    public static volatile MenuFactory instance;

    private MenuFactory() {
    }

    public static MenuOption createMenu(MenuType type) {
        switch (type) {
            case MAIN:
                return new MainMenuOption();
            case CLIENT:
                return new ClientMenuOption();
            case SUBMIT_CLIENT_CREATION:
                return new MainMenuOption();
            case TRANSACTIONS:
                return new MainMenuOption();
        }
    }

    public static synchronized MenuFactory getInstance() {
        MenuFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (MainMenuOption.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MenuFactory();
                }
            }
        }
        return localInstance;
    }
}*/
