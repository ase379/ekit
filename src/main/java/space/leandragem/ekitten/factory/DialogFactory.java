package space.leandragem.ekitten.factory;

import space.leandragem.ekitten.component.PropertiesDialog;
import space.leandragem.util.Translatrix;

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

    protected static Point getTopLeftWindowPointForCurrentScreen(Component dialogOrFrame) {
        Dimension windowSize = dialogOrFrame.getSize();
        var gc = dialogOrFrame.getGraphicsConfiguration();
        var gcBounds = gc.getBounds();
        var dx = gcBounds.x + (gcBounds.width - windowSize.width) / 2;
        var dy = gcBounds.y + (gcBounds.height - windowSize.height) / 2;
        return new Point(dx,dy);
    }

    public static void centerDialog(final JDialog dialog) {
        Rectangle dim = dialog.getGraphicsConfiguration().getBounds();
        var point = getTopLeftWindowPointForCurrentScreen(dialog);
        dialog.setLocation(point.x , point.y);
    }

    public PropertiesDialog newPropertiesDialog(Frame parent, String[] fields, String[] types, String[] values, String title, boolean bModal) {
        PropertiesDialog dlg = new PropertiesDialog(parent, fields, types, values, title, bModal);
        centerDialog(dlg);
        return dlg;
    }
}
