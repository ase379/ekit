package space.leandragem.ekitten.images;

import space.leandragem.util.Load;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * a factory for obtaining the images.
 * Reimplement the factory for using other sources for your images.
 */
public class ImageFactory {

    private static ImageFactory instance = null;

    public static void setInstance(ImageFactory factory) {
        instance = factory;
    }

    public static ImageFactory getInstance() {
        if (instance == null) {
            instance = new ImageFactory();
        }
        return instance;
    }

    /** Fetches the image icon.
     *
     */
    public ImageIcon getImageIcon(String iconName)
    {
        URL imageURL = Load.class.getResource("/space/leandragem/ekitten/icons/" + iconName + "HK.png");
        if(imageURL != null)
        {
            return new ImageIcon(Toolkit.getDefaultToolkit().getImage(imageURL));
        }
        imageURL = Load.class.getResource("/space/leandragem/ekitten/icons/" + iconName + "HK.gif");
        if(imageURL != null)
        {
            return new ImageIcon(Toolkit.getDefaultToolkit().getImage(imageURL));
        }
        return null;
    }
}
