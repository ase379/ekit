/*
GNU Lesser General Public License

AlignAction
Copyright (C) 2009 Howard Kistler

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

package com.hexidec.ekit.action;

import java.awt.event.ActionEvent;
import java.net.PasswordAuthentication;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;

import com.hexidec.ekit.EkitCore;

public class AlignAction extends StyledEditorKit.AlignmentAction
{
	protected EkitCore parent;
	private final String alignment;
	public AlignAction(EkitCore parent, String actionName, int actionType)
	{
		super(actionName, actionType);
		switch (actionType) {
			case StyleConstants.ALIGN_LEFT:
				alignment = "left";
				break;
			case StyleConstants.ALIGN_RIGHT:
				alignment = "right";
				break;
			case StyleConstants.ALIGN_CENTER:
				alignment = "center";
				break;
			case StyleConstants.ALIGN_JUSTIFIED:
				alignment = "justify";
				break;
			default:
				throw new RuntimeException("Invalid StyleConstant ("+actionType+") for align");
		}
		this.parent = parent;
	}

	public void actionPerformed(ActionEvent ae)
	{
		// remember selection as it is lost when setting the attributes
		int selStart = parent.getTextPane().getSelectionStart();
		int selEnd = parent.getTextPane().getSelectionEnd();

		// get current attribute for the paragraph and remove and readd the align attribute
		SimpleAttributeSet sasPara = new SimpleAttributeSet(parent.getTextPane().getParagraphAttributes());
		sasPara.removeAttribute(HTML.Attribute.ALIGN);
		sasPara.addAttribute(HTML.Attribute.ALIGN, alignment);
		setParagraphAttributes(parent.getTextPane(),sasPara, true);

		// get back the old state.
		parent.getTextPane().setText(parent.getTextPane().getText());
		parent.getTextPane().setSelectionStart(selStart);
		parent.getTextPane().setSelectionEnd(selEnd);
	}
}