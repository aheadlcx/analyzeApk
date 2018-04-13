package cn.v6.sixrooms.utils;

import java.util.regex.Pattern;

public class Html2Text {
    public static void main(String[] strArr) {
    }

    public static String Html2Text(String str) {
        String str2 = "";
        try {
            str2 = Pattern.compile("<[^>]+", 2).matcher(Pattern.compile("<[^>]+>", 2).matcher(Pattern.compile("<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>", 2).matcher(Pattern.compile("<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>", 2).matcher(str).replaceAll("")).replaceAll("")).replaceAll("")).replaceAll("");
        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }
        return str2;
    }
}
