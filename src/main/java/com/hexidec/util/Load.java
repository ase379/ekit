package com.hexidec.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

public class Load {

    public static String FileInBase64(File file)
        throws IOException
    {
        byte[] fileBin = Load.FileBinary(file);
        byte[] encoded = Base64.getEncoder().encode(fileBin);

        return new String(encoded);
    }
    
    public static byte[] FileBinary(File file)
        throws IOException
    {
        byte[] bytes;
        try (InputStream is = new FileInputStream(file)) {
            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                throw new IOException("File to large " + file.getName());
            }
            bytes = new byte[(int) length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
        }
        return bytes;
    }

}
