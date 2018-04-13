package com.sprite.ads.internal.net;

import com.sprite.ads.internal.bean.ResponseData;
import java.io.IOException;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.f;

class a$3 implements f {
    final /* synthetic */ b a;

    a$3(b bVar) {
        this.a = bVar;
    }

    public void onFailure(e eVar, IOException iOException) {
        this.a.a(new ADNetException());
    }

    public void onResponse(e eVar, aa aaVar) {
        this.a.a(new ResponseData(aaVar.h().f()));
    }
}
