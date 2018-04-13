package com.bumptech.glide.load.b;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.a;
import java.io.InputStream;
import java.io.OutputStream;

public class h implements a<g> {
    private final a<InputStream> a;
    private final a<ParcelFileDescriptor> b;
    private String c;

    public h(a<InputStream> aVar, a<ParcelFileDescriptor> aVar2) {
        this.a = aVar;
        this.b = aVar2;
    }

    public boolean a(g gVar, OutputStream outputStream) {
        if (gVar.a() != null) {
            return this.a.a(gVar.a(), outputStream);
        }
        return this.b.a(gVar.b(), outputStream);
    }

    public String a() {
        if (this.c == null) {
            this.c = this.a.a() + this.b.a();
        }
        return this.c;
    }
}
