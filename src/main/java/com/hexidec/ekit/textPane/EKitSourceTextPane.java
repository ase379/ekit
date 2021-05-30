package com.hexidec.ekit.textPane;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class EKitSourceTextPane extends JTextArea {

    /** creates a html source text pane
     *
     * @param doc The doc to be used, null if none.
     */

    public EKitSourceTextPane(StyledDocument doc) {
        super(doc);
        this.setBackground(new Color(212, 212, 212));
        this.setSelectionColor(new Color(255, 192, 192));
        this.setMargin(new Insets(4, 4, 4, 4));
        this.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        this.setColumns(1024);
    }
}
