package org.example.menu;

import java.util.*;

public abstract class MenuOption implements MenuSelect {
    protected static final Scanner scan = new Scanner(System.in);
    protected MenuOption root;
    protected String title;
    protected List<MenuSelect> menuOptions = new ArrayList<>();
    protected Map<Integer, MenuSelect> menuMap = new HashMap<>();

    public MenuOption(MenuOption root, String title) {
        this.root = root;
        this.title = title;
    }

    public MenuOption getRoot() {
        return root;
    }

    public void setRoot(MenuOption root) {
        this.root = root;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void selectOption(String selectNumber) {
        int menuPosition = 0;
        try {
            menuPosition = Integer.parseInt(selectNumber);
        } catch (NumberFormatException e){
            System.out.println(MenuOptionManager.ERROR_INPUT);
        }
        MenuSelect selectedMenu = menuMap.getOrDefault(menuPosition, getRoot());
        if (selectedMenu instanceof MenuOption) {
            ((MenuOption) selectedMenu).process((MenuOption) selectedMenu);
        }
    }

    public void process(MenuOption menuOption) {
        menuOption.printMenu();
        String selectNumberInString = scan.next();
        menuOption.selectOption(selectNumberInString);
    }

    public void addMenuOption(int position, MenuOption menuOption) {
        menuOptions.add(position - 1, menuOption);
    }

    public void printMenu() {
        printOptions(menuOptions);
    }

    protected <T extends MenuSelect> void printOptions(Collection<T> menuOptions) {
        int menuPosition = 1;
        StringBuilder builder = new StringBuilder();
        builder.append(this.getTitle()).append("\n");
        for (MenuSelect menuSelect : menuOptions) {
            menuMap.put(menuPosition, menuSelect);
            builder.append(menuPosition++).append(". ").append(menuSelect.getTitle()).append("\n");
        }
        MenuOption lastMenuOption;
        if (root == null) {
            lastMenuOption = new BackMenuOption(null, MenuOptionManager.EXIT);
        } else {
            lastMenuOption = new BackMenuOption(root, MenuOptionManager.BACK);
        }
        menuMap.put(menuPosition, lastMenuOption);
        builder.append(menuPosition).append(". ").append(lastMenuOption.getTitle()).append("\n");
        System.out.println(builder);
    }
}