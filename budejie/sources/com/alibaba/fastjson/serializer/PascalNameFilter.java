package com.alibaba.fastjson.serializer;

public class PascalNameFilter implements NameFilter {
    public String process(Object obj, String str, Object obj2) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] toCharArray = str.toCharArray();
        toCharArray[0] = Character.toUpperCase(toCharArray[0]);
        return new String(toCharArray);
    }
}
