package space.leandragem.ekitten.dialogs;

import com.swabunga.spell.swing.JSpellDialog;
import space.leandragem.ekitten.EkittenCore;

import javax.swing.*;
import java.awt.*;

/**
 * a factory for dialogs.
 * Use setInstance to inject your own implementation. Else the default is used.
 */
public class DialogFactory {

    private static DialogFactory instance = null;

    public static void setInstance(DialogFactory factory) {
        instance = factory;
    }

    public static DialogFactory getInstance() {
        if (instance == null) {
            instance = new DialogFactory();
        }
        return instance;
    };

    protected static Point getTopLeftWindowPointForCurrentScreen(Window dialogOrFrame) {
        Dimension windowSize = dialogOrFrame.getSize();
        var gc = dialogOrFrame.getGraphicsConfiguration();
        var gcBounds = gc.getBounds();
        var dx = gcBounds.x + (gcBounds.width - windowSize.width) / 2;
        var dy = gcBounds.y + (gcBounds.height - windowSize.height) / 2;
        return new Point(dx,dy);
    }

    public static void centerWindow(final Window window) {
        Rectangle dim = window.getGraphicsConfiguration().getBounds();
        var point = getTopLeftWindowPointForCurrentScreen(window);
        window.setLocation(point.x , point.y);
    }


    public PropertiesDialog newPropertiesDialog(Frame parent, String[] fields, String[] types, String[] values, String title, boolean bModal) {
        PropertiesDialog dlg = new PropertiesDialog(parent, fields, types, values, title, bModal);
        centerWindow(dlg);
        return dlg;
    }

    public JSpellDialog newSpellDialog(Frame owner, String title, boolean modal) {
        JSpellDialog dlg = new JSpellDialog(owner,title, modal);
        centerWindow(dlg);
        return dlg;
    }


    public FontSelectorDialog newFontSelectorDialog(Frame parent, String title, boolean bModal, String attribName, String demoText) {
        FontSelectorDialog dlg = new FontSelectorDialog(parent, title, bModal,attribName,demoText);
        centerWindow(dlg);
        return dlg;
    }


    public ImageFileDialog newImageFileDialog(Frame parent, String imgDir, String[] imgExts, String imgDesc, String imgSrc, String title, boolean bModal) {
        ImageFileDialog dlg = new ImageFileDialog(parent,imgDir, imgExts,imgDesc,imgSrc,title,bModal);
        centerWindow(dlg);
        return dlg;
    }


    public SearchDialog newSearchDialog(Frame parent, String title, boolean bModal, boolean bIsReplace, boolean bCaseSetting, boolean bTopSetting) {
        SearchDialog dlg = new SearchDialog(parent,title,bModal,bIsReplace,bCaseSetting,bTopSetting);
        centerWindow(dlg);
        return dlg;
    }

    public ImageURLDialog newImageURLDialog(Frame parent, String title, boolean bModal) {
        ImageURLDialog dlg = new ImageURLDialog(parent,title,bModal);
        centerWindow(dlg);
        return dlg;
    }

    public SimpleInfoDialog newSimpleInfoDialog(Frame parent, String title, boolean bModal, String message, int type) {
        SimpleInfoDialog dlg = new SimpleInfoDialog(parent,title,bModal,message,type);
        centerWindow(dlg);
        return dlg;
    }

    public UserInputAnchorDialog newSimpleInfoDialog(EkittenCore peKit, String title, boolean bModal, String defaultAnchor) {
        UserInputAnchorDialog dlg = new UserInputAnchorDialog(peKit,title,bModal,defaultAnchor);
        centerWindow(dlg);
        return dlg;
    }


}
