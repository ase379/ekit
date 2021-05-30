package com.hexidec.ekit.text.handlers;

import javax.swing.*;

public class EkitTextPane extends JTextPane {

    public EkitTextPane() {
        var hyperlinkListener = new HyperlinkHandler(this);
        this.addMouseListener(hyperlinkListener);
        this.addMouseMotionListener(hyperlinkListener);
    }
}
