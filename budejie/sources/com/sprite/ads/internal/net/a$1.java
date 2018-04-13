package com.sprite.ads.internal.net;

import java.io.IOException;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.f;

class a$1 implements f {
    final /* synthetic */ b a;

    a$1(b bVar) {
        this.a = bVar;
    }

    public void onFailure(e eVar, IOException iOException) {
        this.a.a(new ADNetException(iOException));
    }

    public void onResponse(e eVar, aa aaVar) {
        this.a.a(null);
    }
}
