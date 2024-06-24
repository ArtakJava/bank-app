package org.example;

import org.example.menu.MainMenuOption;

public class Application {

    public static void main(String[] args) {
        MainMenuOption mainMenu = new MainMenuOption(null);
        mainMenu.process(mainMenu);
    }
}
