package com.budejie.www.activity.image;

import java.util.Comparator;

class a$1 implements Comparator {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public int compare(Object obj, Object obj2) {
        return (int) (((ImageItem) obj2).modifiedTime - ((ImageItem) obj).modifiedTime);
    }
}
