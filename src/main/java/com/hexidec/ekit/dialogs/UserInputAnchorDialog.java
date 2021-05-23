/*
GNU Lesser General Public License

UserInputAnchorDialog
Copyright (C) 2000 Howard Kistler & other contributors

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

import com.hexidec.ekit.EkitCore;
import com.hexidec.util.Translatrix;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UserInputAnchorDialog extends JDialog implements ActionListener
{
	private String inputText = null;
	private final JTextField jtxfInput = new JTextField(32);

	UserInputAnchorDialog(EkitCore peKit, String title, boolean bModal, String defaultAnchor)
	{		
		super(peKit.getFrame(), title, bModal, peKit.getGraphicsConfiguration());
		jtxfInput.setText(defaultAnchor);
		init();
	}

   	public void actionPerformed(ActionEvent e)
   	{
		if(e.getActionCommand().equals("accept"))
		{
			inputText = jtxfInput.getText();
			setVisible(false);
		}	
	  	if(e.getActionCommand().equals("cancel"))
		{
			inputText = null;
			setVisible(false);
		}
	}

	public void init()
	{
	  	Container contentPane = getContentPane();
	  	contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	  	setBounds(100,100,400,300);
	  	setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

	  	JPanel centerPanel = new JPanel();
	  	centerPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
       	centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

	  	JLabel anchorLabel = new JLabel(Translatrix.getTranslationString("InsertAnchor"), SwingConstants.LEFT);
	  	centerPanel.add(anchorLabel);
	  	centerPanel.add(Box.createRigidArea(new Dimension(5,0)));
	  	centerPanel.add(jtxfInput);

		JPanel buttonPanel= new JPanel();	  	
//	  	buttonPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));

		JButton saveButton = new JButton(Translatrix.getTranslationString("DialogAccept"));
	  	saveButton.setActionCommand("accept");
		saveButton.addActionListener(this);

	  	JButton cancelButton = new JButton(Translatrix.getTranslationString("DialogClose") );
	  	cancelButton.setActionCommand("cancel");
		cancelButton.addActionListener(this);

		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);

		contentPane.add(centerPanel);
		contentPane.add(buttonPanel);

 		this.pack();
		DialogFactory.getInstance().centerWindow(this);
		this.setVisible(true);
   	}

	public String getInputText()
	{
		return inputText;
	}

	public void setAnchor(String anchor)
	{
		jtxfInput.setText(anchor);
	}
}

