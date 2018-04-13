package com.bumptech.glide.load.b;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.a.c;
import java.io.InputStream;

public class f<A> implements l<A, g> {
    private final l<A, InputStream> a;
    private final l<A, ParcelFileDescriptor> b;

    static class a implements c<g> {
        private final c<InputStream> a;
        private final c<ParcelFileDescriptor> b;

        public /* synthetic */ Object a(Priority priority) throws Exception {
            return b(priority);
        }

        public a(c<InputStream> cVar, c<ParcelFileDescriptor> cVar2) {
            this.a = cVar;
            this.b = cVar2;
        }

        public g b(Priority priority) throws Exception {
            InputStream inputStream;
            ParcelFileDescriptor parcelFileDescriptor = null;
            if (this.a != null) {
                try {
                    inputStream = (InputStream) this.a.a(priority);
                } catch (Throwable e) {
                    if (Log.isLoggable("IVML", 2)) {
                        Log.v("IVML", "Exception fetching input stream, trying ParcelFileDescriptor", e);
                    }
                    if (this.b == null) {
                        throw e;
                    }
                }
                if (this.b != null) {
                    try {
                        parcelFileDescriptor = (ParcelFileDescriptor) this.b.a(priority);
                    } catch (Throwable e2) {
                        if (Log.isLoggable("IVML", 2)) {
                            Log.v("IVML", "Exception fetching ParcelFileDescriptor", e2);
                        }
                        if (inputStream == null) {
                            throw e2;
                        }
                    }
                }
                return new g(inputStream, parcelFileDescriptor);
            }
            inputStream = null;
            if (this.b != null) {
                parcelFileDescriptor = (ParcelFileDescriptor) this.b.a(priority);
            }
            return new g(inputStream, parcelFileDescriptor);
        }

        public void a() {
            if (this.a != null) {
                this.a.a();
            }
            if (this.b != null) {
                this.b.a();
            }
        }

        public String b() {
            if (this.a != null) {
                return this.a.b();
            }
            return this.b.b();
        }

        public void c() {
            if (this.a != null) {
                this.a.c();
            }
            if (this.b != null) {
                this.b.c();
            }
        }
    }

    public f(l<A, InputStream> lVar, l<A, ParcelFileDescriptor> lVar2) {
        if (lVar == null && lVar2 == null) {
            throw new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
        }
        this.a = lVar;
        this.b = lVar2;
    }

    public c<g> a(A a, int i, int i2) {
        c a2;
        c a3;
        if (this.a != null) {
            a2 = this.a.a(a, i, i2);
        } else {
            a2 = null;
        }
        if (this.b != null) {
            a3 = this.b.a(a, i, i2);
        } else {
            a3 = null;
        }
        if (a2 == null && a3 == null) {
            return null;
        }
        return new a(a2, a3);
    }
}
