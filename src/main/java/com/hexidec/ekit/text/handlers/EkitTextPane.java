package com.hexidec.ekit.text.handlers;

import javax.swing.*;
import java.awt.*;

public class EkitTextPane extends JTextPane {

    public EkitTextPane() {
        this.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        this.setMargin(new Insets(4, 4, 4, 4));
        var hyperlinkListener = new HyperlinkHandler(this);
        this.addMouseListener(hyperlinkListener);
        this.addMouseMotionListener(hyperlinkListener);
    }
}
