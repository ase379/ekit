package com.hexidec.ekit.utils;

public class StringUtils {

    public static boolean doesStringContainNonWhitespaceChars(String text) {
        for(char c : text.toCharArray())
        {
            if(!Character.isWhitespace(c))
            {
                return true;
            }
        }
        return false;
    }

}
