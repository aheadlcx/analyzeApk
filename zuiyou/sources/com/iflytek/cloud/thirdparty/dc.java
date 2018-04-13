package com.iflytek.cloud.thirdparty;

public class dc {
    public static final String[] a = new String[]{"134", "135", "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159", "182", "187", "188"};
    public static final String[] b = new String[]{"130", "131", "132", "155", "156", "185", "186"};
    public static final String[] c = new String[]{"133", "153", "180", "189"};

    public static String a(String str) {
        String str2 = "";
        if (str == null) {
            return str;
        }
        str = str.replaceAll(" ", "").replaceAll("-", "");
        StringBuilder stringBuilder = new StringBuilder(str);
        return stringBuilder.length() > 5 ? (stringBuilder.substring(0, 3).equals("+86") || stringBuilder.substring(0, 3).equals("086")) ? stringBuilder.substring(3, stringBuilder.length()) : stringBuilder.substring(0, 2).equals("86") ? stringBuilder.substring(2, stringBuilder.length()) : (stringBuilder.substring(0, 5).equals("12530") || stringBuilder.substring(0, 5).equals("12520") || stringBuilder.substring(0, 5).equals("17951") || stringBuilder.substring(0, 5).equals("17911") || stringBuilder.subSequence(0, 5).equals("12593")) ? stringBuilder.substring(5, stringBuilder.length()) : str : str;
    }

    public static String a(String[] strArr, char c) {
        StringBuilder stringBuilder = new StringBuilder();
        if (strArr != null) {
            for (String str : strArr) {
                if (str != null) {
                    stringBuilder.append(str);
                    stringBuilder.append(c);
                }
            }
        }
        return stringBuilder.toString();
    }
}
