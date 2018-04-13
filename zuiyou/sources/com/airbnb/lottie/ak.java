package com.airbnb.lottie;

import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;

class ak {
    private final Path a = new Path();
    private final List<n<?, Path>> b;
    private final List<Mask> c;

    ak(List<Mask> list) {
        this.c = list;
        this.b = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            this.b.add(((Mask) list.get(i)).b().b());
        }
    }

    List<Mask> a() {
        return this.c;
    }

    List<n<?, Path>> b() {
        return this.b;
    }
}
