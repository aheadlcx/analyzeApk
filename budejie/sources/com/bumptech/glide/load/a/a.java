package com.bumptech.glide.load.a;

import android.content.res.AssetManager;
import android.util.Log;
import com.bumptech.glide.Priority;
import java.io.IOException;

public abstract class a<T> implements c<T> {
    private final String a;
    private final AssetManager b;
    private T c;

    protected abstract T a(AssetManager assetManager, String str) throws IOException;

    protected abstract void a(T t) throws IOException;

    public a(AssetManager assetManager, String str) {
        this.b = assetManager;
        this.a = str;
    }

    public T a(Priority priority) throws Exception {
        this.c = a(this.b, this.a);
        return this.c;
    }

    public void a() {
        if (this.c != null) {
            try {
                a(this.c);
            } catch (Throwable e) {
                if (Log.isLoggable("AssetUriFetcher", 2)) {
                    Log.v("AssetUriFetcher", "Failed to close data", e);
                }
            }
        }
    }

    public String b() {
        return this.a;
    }

    public void c() {
    }
}
