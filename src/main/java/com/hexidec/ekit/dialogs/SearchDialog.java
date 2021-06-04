/*
GNU Lesser General Public License

SearchDialog
Copyright (C) 2000 Howard Kistler

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
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** Class for providing a dialog that lets the user specify arguments for
  * the Search Find/Replace functions
  */
public class SearchDialog extends JDialog
{
	private String inputFindTerm    = null;
	private String inputReplaceTerm = null;
	private boolean bCaseSensitive  = false;
	private boolean bStartAtTop     = false;
	private boolean bReplaceAll     = false;
	final JTextField jtxfFindTerm    = new JTextField(3);
	private final JOptionPane jOptionPane;

	public SearchDialog(Frame parent, String title, boolean bModal, boolean bIsReplace, boolean bCaseSetting, boolean bTopSetting)
	{
		super(parent, title, bModal, parent.getGraphicsConfiguration());
		final boolean isReplaceDialog    = bIsReplace;
		final JTextField jtxfReplaceTerm = new JTextField(3);
		final JCheckBox  jchkCase        = new JCheckBox(Translatrix.getTranslationString("SearchCaseSensitive"), bCaseSetting);
		final JCheckBox  jchkTop         = new JCheckBox(Translatrix.getTranslationString("SearchStartAtTop"), bTopSetting);
		final JCheckBox  jchkAll         = new JCheckBox(Translatrix.getTranslationString("SearchReplaceAll"), false);
		final Object[] buttonLabels      = { Translatrix.getTranslationString("DialogAccept"), Translatrix.getTranslationString("DialogCancel") };
		Object[] panelContents;
		if(bIsReplace)
		{
			panelContents = new Object[]{
					Translatrix.getTranslationString("SearchFind"),
					jtxfFindTerm,
					Translatrix.getTranslationString("SearchReplace"),
					jtxfReplaceTerm,
					jchkAll,
					jchkCase,
					jchkTop
			};
		}
		else
		{
			panelContents = new Object[]{
					Translatrix.getTranslationString("SearchFind"),
					jtxfFindTerm,
					jchkCase,
					jchkTop
			};
		}
		jOptionPane = new JOptionPane(panelContents, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, buttonLabels, buttonLabels[0]);
		setContentPane(jOptionPane);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we)
			{
				jOptionPane.setValue(Integer.valueOf(JOptionPane.CLOSED_OPTION));
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
				{
					inputFindTerm  = jtxfFindTerm.getText();
					bCaseSensitive = jchkCase.isSelected();
					bStartAtTop    = jchkTop.isSelected();
					if(isReplaceDialog)
					{
						inputReplaceTerm = jtxfReplaceTerm.getText();
						bReplaceAll      = jchkAll.isSelected();
					}
				}
				else
				{
					inputFindTerm    = null;
					inputReplaceTerm = null;
					bCaseSensitive   = false;
					bStartAtTop      = false;
					bReplaceAll      = false;
				}
				setVisible(false);
			}
		});
		this.pack();
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);
		jtxfFindTerm.requestFocus();
	}

	public String  getFindTerm()      { return inputFindTerm; }
	public String  getReplaceTerm()   { return inputReplaceTerm; }
	public boolean getCaseSensitive() { return bCaseSensitive; }
	public boolean getStartAtTop()    { return bStartAtTop; }
	public boolean getReplaceAll()    { return bReplaceAll; }
}

