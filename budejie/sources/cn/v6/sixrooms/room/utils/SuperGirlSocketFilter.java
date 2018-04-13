package cn.v6.sixrooms.room.utils;

import java.util.List;

public class SuperGirlSocketFilter {
    private static List<String> a = new d();

    public static boolean isFiltered(String str) {
        return a.contains(str);
    }
}
