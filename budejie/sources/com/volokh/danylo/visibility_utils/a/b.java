package com.volokh.danylo.visibility_utils.a;

import android.view.View;
import com.volokh.danylo.visibility_utils.a.d.a;

public class b implements a<com.volokh.danylo.visibility_utils.b.a> {
    private static final String a = b.class.getSimpleName();

    public void a(com.volokh.danylo.visibility_utils.b.a aVar, View view, int i, boolean z, boolean z2) {
        aVar.setActive(view, i, z, z2);
    }

    public void a(com.volokh.danylo.visibility_utils.b.a aVar, View view, int i) {
        aVar.deactivate(view, i);
    }
}
