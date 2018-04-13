package com.bumptech.glide.d;

import com.bumptech.glide.i.h;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

class a implements g {
    private final Set<h> a = Collections.newSetFromMap(new WeakHashMap());
    private boolean b;
    private boolean c;

    a() {
    }

    public void a(h hVar) {
        this.a.add(hVar);
        if (this.c) {
            hVar.onDestroy();
        } else if (this.b) {
            hVar.onStart();
        } else {
            hVar.onStop();
        }
    }

    void a() {
        this.b = true;
        for (h onStart : h.a(this.a)) {
            onStart.onStart();
        }
    }

    void b() {
        this.b = false;
        for (h onStop : h.a(this.a)) {
            onStop.onStop();
        }
    }

    void c() {
        this.c = true;
        for (h onDestroy : h.a(this.a)) {
            onDestroy.onDestroy();
        }
    }
}
