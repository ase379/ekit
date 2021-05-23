package com.hexidec.ekit.editor.menus;

import com.hexidec.ekit.images.ImageFactory;
import com.hexidec.util.Translatrix;

import javax.swing.*;

import java.awt.event.ActionListener;

import static com.hexidec.ekit.editor.Command.CMD_CLIP_CUT;

/**
 * a factory for creating the menu items.
 * Reimplement the factory for using other sources for your images.
 */
public class MenuItemFactory {

    private static MenuItemFactory instance = null;

    private boolean menuIconsVisible;

    public static void setInstance(MenuItemFactory factory) {
        instance = factory;
    }

    public static MenuItemFactory getInstance() {
        if (instance == null) {
            instance = new MenuItemFactory();
        }
        return instance;
    }

    public void setMenuIconsVisible(boolean menuIconsVisible) {
        this.menuIconsVisible = menuIconsVisible;
    }


    public JMenuItem createMenuItem(String text, String actionCommand, ActionListener actionListener, KeyStroke stroke, String iconName) {
        var item = new JMenuItem(Translatrix.getTranslationString(text));
        item.setActionCommand(actionCommand);
        if (actionListener != null)
            item.addActionListener(actionListener);
        item.setAccelerator(stroke);
        if(menuIconsVisible) {
            item.setIcon(ImageFactory.getInstance().getImageIcon(iconName));
        }
        return item;
    }
}
