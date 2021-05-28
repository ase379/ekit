package com.hexidec.ekit.component;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class EkitHyperlinkListener implements MouseListener, MouseMotionListener {

    private final JTextPane textPane;
    private boolean handCursor;
    private String linkHRef = "";

    public EkitHyperlinkListener(JTextPane textPane) {
        this.textPane = textPane;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved( MouseEvent event )
    {
        if (event == null)
            return;
        if (event.isConsumed())
            return;
        if ( ! textPane.equals(event.getComponent()))
            return;

        // das Text-Element an der Mouse-Position ermitteln
        int     pos  = textPane.viewToModel2D(event.getPoint());
        Element elem = ((StyledDocument) textPane.getDocument()).getCharacterElement(pos);
        linkHRef = "";
        if (elem != null)
        {
            AttributeSet set = elem.getAttributes();
            if (set != null)
            {
                Object attribute = set.getAttribute(HTML.Tag.A);
                if (attribute instanceof AttributeSet) {
                    AttributeSet as = (AttributeSet) attribute;
                    linkHRef = (String) as.getAttribute(HTML.Attribute.HREF);
                    linkHRef = linkHRef.trim();

                }
                boolean hasValidLink = linkHRef.length() > 0;
                if (hasValidLink && ! handCursor)
                {
                    handCursor  = true;
                    ((HTMLEditorKit)textPane.getEditorKit()).setDefaultCursor(new Cursor(Cursor.HAND_CURSOR) );
                    System.out.println("handcursor on");
                } 
                else if (!hasValidLink && handCursor)
                {
                    handCursor  = false;
                    ((HTMLEditorKit)textPane.getEditorKit()).setDefaultCursor(new Cursor(Cursor.TEXT_CURSOR) );
                    System.out.println("handcursor off");
                }
            }
        }
    }

    @Override
    public void mouseClicked( MouseEvent event )
    {
        if (event == null)
            return;
        if (event.isConsumed())
            return;
        if ( ! SwingUtilities.isLeftMouseButton(event))
            return;
        if (event.getClickCount() > 1)
            return;
        if ( ! textPane.equals(event.getComponent()))
            return;

        try {
            if (!linkHRef.isEmpty())
                Desktop.getDesktop().browse( new java.net.URI(linkHRef));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    } //mouseClicked(MouseEvent)

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
