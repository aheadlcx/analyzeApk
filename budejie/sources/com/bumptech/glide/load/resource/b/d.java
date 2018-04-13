package com.bumptech.glide.load.resource.b;

import com.bumptech.glide.f.b;
import com.bumptech.glide.load.b.o;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.j;
import java.io.File;
import java.io.InputStream;

public class d implements b<InputStream, File> {
    private static final a a = new a();
    private final com.bumptech.glide.load.d<File, File> b = new a();
    private final com.bumptech.glide.load.a<InputStream> c = new o();

    private static class a implements com.bumptech.glide.load.d<InputStream, File> {
        private a() {
        }

        public j<File> a(InputStream inputStream, int i, int i2) {
            throw new Error("You cannot decode a File from an InputStream by default, try either #diskCacheStratey(DiskCacheStrategy.SOURCE) to avoid this call or #decoder(ResourceDecoder) to replace this Decoder");
        }

        public String a() {
            return "";
        }
    }

    public com.bumptech.glide.load.d<File, File> a() {
        return this.b;
    }

    public com.bumptech.glide.load.d<InputStream, File> b() {
        return a;
    }

    public com.bumptech.glide.load.a<InputStream> c() {
        return this.c;
    }

    public e<File> d() {
        return com.bumptech.glide.load.resource.b.b();
    }
}
