package com.bumptech.glide.load.engine.b;

import android.content.Context;
import com.bumptech.glide.load.engine.b.d.a;
import java.io.File;

public final class f extends d {

    /* renamed from: com.bumptech.glide.load.engine.b.f$1 */
    class AnonymousClass1 implements a {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        AnonymousClass1(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        public File a() {
            File externalCacheDir = this.a.getExternalCacheDir();
            if (externalCacheDir == null) {
                return null;
            }
            return this.b != null ? new File(externalCacheDir, this.b) : externalCacheDir;
        }
    }

    public f(Context context, int i) {
        this(context, "image_manager_disk_cache", i);
    }

    public f(Context context, String str, int i) {
        super(new AnonymousClass1(context, str), i);
    }
}
