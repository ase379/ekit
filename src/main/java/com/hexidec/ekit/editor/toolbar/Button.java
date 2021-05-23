package com.hexidec.ekit.editor.toolbar;

import com.hexidec.ekit.component.JButtonNoFocus;
import com.hexidec.ekit.images.ImageFactory;
import com.hexidec.util.Load;
import com.hexidec.util.Translatrix;

import static com.hexidec.ekit.editor.Command.*;

public enum Button {
    NEW("NW", CMD_DOC_NEW, "New", "NewDocument"),
    OPEN("OP", CMD_DOC_OPEN_HTML, "Open", "OpenDocument"),
    SAVE("SV", CMD_DOC_SAVE, "Save", "SaveDocument"),
    PRINT("PR", CMD_DOC_PRINT, "Print", "PrintDocument"),
    CUT("CT", CMD_CLIP_CUT, "Cut", "Cut"),
    COPY("CP", CMD_CLIP_COPY, "Copy", "Copy"),
    PASTE("PS", CMD_CLIP_PASTE, "Paste", "Paste"),
    UNDO("UN", CMD_UNDO, "Undo", "Undo"),
    REDO("RE", CMD_REDO, "Redo", "Redo");

    private final String code;
    private final String action;
    private final String icon;
    private final String translationString;

    Button(
        String code,
        String action,
        String icon,
        String translationString
    )
    {
        this.code = code;
        this.action = action;
        this.icon = icon;
        this.translationString = translationString;
    }

    public JButtonNoFocus buildButton()
    {
        JButtonNoFocus btn = new JButtonNoFocus();
        btn.setActionCommand(action);
        btn.setIcon(ImageFactory.getInstance().getImageIcon(icon));
        btn.setToolTipText(Translatrix.getTranslationString(translationString));
        btn.setText(null);
        return btn;
    }

    public String getCode()
    {
        return code;
    }

}
