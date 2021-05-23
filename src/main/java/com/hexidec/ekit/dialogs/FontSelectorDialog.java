/*
GNU Lesser General Public License

FontSelectorDialog
Copyright (C) 2003 Howard Kistler

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package com.hexidec.ekit.dialogs;

import com.hexidec.util.Translatrix;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

/** Class for providing a dialog that lets the user specify values for tag attributes
  */
public class FontSelectorDialog extends JDialog implements ItemListener
{
	private final JComboBox<String> jcmbFontlist;
	private String fontName = "";
	private final JOptionPane jOptionPane;
	private final JTextPane jtpFontPreview;
	private final String defaultText;

	FontSelectorDialog(Frame parent, String title, boolean bModal, String attribName, String demoText)
	{
		super(parent, title, bModal, parent.getGraphicsConfiguration());

		if(demoText != null && demoText.length() > 0)
		{
			if(demoText.length() > 24)
			{
				defaultText = demoText.substring(0, 24);
			}
			else
			{
				defaultText = demoText;
			}
		}
		else
		{
			defaultText = "aAbBcCdDeEfFgGhH,.0123";
		}

		/* Obtain available fonts */
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		Vector<String> vcFontnames = new Vector<>(fonts.length - 5);
		for (String font : fonts) {
			if (!font.equals("Dialog") && !font.equals("DialogInput") && !font.equals("Monospaced") && !font.equals("SansSerif") && !font.equals("Serif")) {
				vcFontnames.add(font);
			}
		}
		jcmbFontlist = new JComboBox<>(vcFontnames);
		jcmbFontlist.addItemListener(this);

		jtpFontPreview = new JTextPane();
		final HTMLEditorKit kitFontPreview = new HTMLEditorKit();
		final HTMLDocument docFontPreview = (HTMLDocument)(kitFontPreview.createDefaultDocument());
		jtpFontPreview.setEditorKit(kitFontPreview);
		jtpFontPreview.setDocument(docFontPreview);
		jtpFontPreview.setMargin(new Insets(4, 4, 4, 4));
		jtpFontPreview.setBounds(0, 0, 120, 18);
		jtpFontPreview.setText(getFontSampleString(defaultText));
		Object[] panelContents = { Translatrix.getTranslationString("FontSample"), jtpFontPreview, attribName, jcmbFontlist };
		final Object[] buttonLabels = { Translatrix.getTranslationString("DialogAccept"), Translatrix.getTranslationString("DialogCancel") };

		jOptionPane = new JOptionPane(panelContents, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, buttonLabels, buttonLabels[0]);
		setContentPane(jOptionPane);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we)
			{
				jOptionPane.setValue(JOptionPane.CLOSED_OPTION);
			}
		});

		jOptionPane.addPropertyChangeListener(e -> {
			String prop = e.getPropertyName();
			if(isVisible()
				&& (e.getSource() == jOptionPane)
				&& (prop.equals(JOptionPane.VALUE_PROPERTY) || prop.equals(JOptionPane.INPUT_VALUE_PROPERTY)))
			{
				Object value = jOptionPane.getValue();
				if(value == JOptionPane.UNINITIALIZED_VALUE)
				{
					return;
				}
				jOptionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
				if(value.equals(buttonLabels[0]))
					fontName = (String)(jcmbFontlist.getSelectedItem());
				else
					fontName = null;
				setVisible(false);
			}
		});
		this.pack();
		this.setVisible(true);
	}

	/* ItemListener method */
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getStateChange() == ItemEvent.SELECTED)
		{
			jtpFontPreview.setText(getFontSampleString(defaultText));
		}
	}

	public String getFontName()
	{
		return fontName;
	}

	private String getFontSampleString(String demoText)
	{
		return "<HTML><BODY><FONT FACE=" + '"' + jcmbFontlist.getSelectedItem() + '"' + ">" + demoText + "</FONT></BODY></HTML>";
	}

}

