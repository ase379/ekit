package com.hexidec.ekit;

import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

/** a POJO holding the settings and current state of a EkitCore.
 */
public class EKitCoreSettings {

    public boolean exclusiveEdit = true;
    public boolean isParentApplet;
    public String sDocument;
    public String sStyleSheet;
    public String sRawDocument;
    public StyledDocument sdocSource;
    public URL urlStyleSheet;
    public boolean includeToolBar;
    public boolean showViewSource;
    public boolean showMenuIcons;
    public String sLanguage;
    public String sCountry;
    public boolean base64;
    public boolean debugMode;
    public boolean hasSpellChecker;
    public boolean multiBar;
    public String toolbarSeq;
    public boolean preserveUnknownTags = false;

    public String lastSearchFindTerm     = null;
    public String lastSearchReplaceTerm  = null;
    public boolean lastSearchCaseSetting = false;
    public boolean lastSearchTopSetting  = false;

    public File currentFile = null;
    public String imageChooserStartDir = ".";

    public int indent = 0;
    public final int indentStep = 4;

    public boolean enterIsBreak = true;

    // File extensions for MutableFilter
    public final String[] extsHTML = { "html", "htm", "shtml" };
    public final String[] extsCSS  = { "css" };
    public final String[] extsIMG  = { "jpg", "jpeg", "png" };
    public final String[] extsRTF  = { "rtf" };
    public final String[] extsB64  = { "b64" };
    public final String[] extsSer  = { "ser" };

    // Control key equivalents on different systems
    public int CTRLKEY = KeyEvent.CTRL_MASK;
    public boolean modified = false;
    public final String appName;


    public EKitCoreSettings(boolean isParentApplet, String sDocument, String sStyleSheet, String sRawDocument, StyledDocument sdocSource, URL urlStyleSheet, boolean includeToolBar, boolean showViewSource, boolean showMenuIcons, boolean editModeExclusive, String sLanguage, String sCountry, boolean base64, boolean debugMode, boolean hasSpellChecker, boolean multiBar, String toolbarSeq, boolean keepUnknownTags, boolean enterBreak, String appName) {
        this.isParentApplet = isParentApplet;
        this.sDocument = sDocument;
        this.sStyleSheet = sStyleSheet;
        this.sRawDocument = sRawDocument;
        this.sdocSource = sdocSource;
        this.urlStyleSheet = urlStyleSheet;
        this.includeToolBar = includeToolBar;
        this.showViewSource = showViewSource;
        this.showMenuIcons = showMenuIcons;
        this.exclusiveEdit = editModeExclusive;
        this.sLanguage = sLanguage;
        this.sCountry = sCountry;
        this.base64 = base64;
        this.debugMode = debugMode;
        this.hasSpellChecker = hasSpellChecker;
        this.multiBar = multiBar;
        this.toolbarSeq = toolbarSeq;
        this.preserveUnknownTags = keepUnknownTags;
        this.enterIsBreak = enterBreak;
        this.appName = appName;
        /* Initialise system-specific control key value */
        if(!(GraphicsEnvironment.isHeadless()))
        {
            CTRLKEY = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        }
    }
}
