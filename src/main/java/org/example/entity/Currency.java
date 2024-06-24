package org.example.entity;

import org.example.menu.MenuSelect;

public enum Currency implements MenuSelect {
    RUBLE, EURO, DOLLAR;

    @Override
    public String getTitle() {
        return toString();
    }
}