package com.budejie.www.util;

import com.budejie.www.bean.Fans;
import java.util.Comparator;

public class ag implements Comparator<Fans> {
    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((Fans) obj, (Fans) obj2);
    }

    public int a(Fans fans, Fans fans2) {
        if (fans.getSortLetters().equals("@") || fans2.getSortLetters().equals("#")) {
            return -1;
        }
        if (fans.getSortLetters().equals("#") || fans2.getSortLetters().equals("@")) {
            return 1;
        }
        return fans.getSortLetters().compareTo(fans2.getSortLetters());
    }
}
