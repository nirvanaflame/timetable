package com.lizziputt.consoleapp;

import lombok.Builder;
import lombok.ToString;

import java.util.List;

@ToString
@Builder
public class Menu {

    private final String name;
    private final List<Menu> menus;
    private final List<MenuItem> menuItems;

    public void printMenu() {
        System.out.println(name);
        for (int i = 0; i < menus.size(); ) {
            Menu menu = menus.get(i);
            System.out.println(++i + ". " + menu.name);
        }
        System.out.print("> ");
    }

    public void printMenuItems() {
        for (int i = 0; i < menuItems.size(); ) {
            MenuItem menuItem = menuItems.get(i);
            System.out.println(++i + ". " + menuItem.name);
        }
        System.out.print("> ");
    }

    public Menu select(int choice) {
        if (choice > menus.size() || choice < 0) throw new IllegalArgumentException("Invalid menu point!");
        return menus.get(choice - 1);
    }

    public MenuItem selectItem(int choice) {
        if (choice > menuItems.size() || choice < 0) throw new IllegalArgumentException("Invalid menu point!");
        return menuItems.get(choice - 1);
    }

    public record MenuItem(String name, ExecutionStrategy strategy) {}
}