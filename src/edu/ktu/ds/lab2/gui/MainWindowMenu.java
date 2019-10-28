package edu.ktu.ds.lab2.gui;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 *
 * @author darius
 */
public abstract class MainWindowMenu extends MenuBar implements EventHandler<ActionEvent> {

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("edu.ktu.ds.lab2.gui.messages");

    public MainWindowMenu() {
        initComponents();
    }

    private void initComponents() {
        // Sukuriamas meniu      
        Menu menu1 = new Menu(MESSAGES.getString("menu1"));
        MenuItem menuItem11 = new MenuItem(MESSAGES.getString("menuItem11"));
        menuItem11.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        menuItem11.setOnAction(this);
        MenuItem menuItem12 = new MenuItem(MESSAGES.getString("menuItem12"));
        menuItem12.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        menuItem12.setOnAction(this);
        MenuItem menuItem13 = new MenuItem(MESSAGES.getString("menuItem13"));
        menuItem13.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        menuItem13.setOnAction(this);
        menu1.getItems().addAll(menuItem11, menuItem12, new SeparatorMenuItem(), menuItem13);

        Menu menu2 = new Menu(MESSAGES.getString("menu2"));
        MenuItem menuItem21 = new MenuItem(MESSAGES.getString("menuItem21"));
        menuItem21.setAccelerator(new KeyCodeCombination(KeyCode.F1, KeyCombination.SHIFT_DOWN));
        menuItem21.setOnAction(this);
        menu2.getItems().add(menuItem21);

        getMenus().addAll(menu1, menu2);
    }

    @Override
    public abstract void handle(ActionEvent ae);
}
