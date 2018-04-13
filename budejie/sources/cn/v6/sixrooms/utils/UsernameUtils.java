package cn.v6.sixrooms.utils;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class UsernameUtils {
    public static String stringFilter(String str) throws PatternSyntaxException {
        return Pattern.compile("[&'<>\"\\\\]").matcher(str).replaceAll("");
    }
}
