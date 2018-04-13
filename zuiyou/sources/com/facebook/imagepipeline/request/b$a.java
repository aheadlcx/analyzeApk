package com.facebook.imagepipeline.request;

import android.net.Uri;
import com.facebook.imagepipeline.request.b.b;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;

public class b$a {
    private final String a;
    private List<b> b;
    private boolean c;
    private String d;

    private b$a(String str) {
        this.c = false;
        this.d = SocialConstants.TYPE_REQUEST;
        this.a = str;
    }

    public b$a a(Uri uri, int i, int i2, ImageRequest$CacheChoice imageRequest$CacheChoice) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(new b(uri, i, i2, imageRequest$CacheChoice));
        return this;
    }

    public b$a a(boolean z) {
        this.c = z;
        return this;
    }

    public b$a a(String str) {
        this.d = str;
        return this;
    }

    public b a() {
        return new b(this, null);
    }
}
