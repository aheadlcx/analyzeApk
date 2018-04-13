package com.bumptech.glide.load.a;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.Priority;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class g<T> implements c<T> {
    private final Uri a;
    private final Context b;
    private T c;

    protected abstract void a(T t) throws IOException;

    protected abstract T b(Uri uri, ContentResolver contentResolver) throws FileNotFoundException;

    public g(Context context, Uri uri) {
        this.b = context.getApplicationContext();
        this.a = uri;
    }

    public final T a(Priority priority) throws Exception {
        this.c = b(this.a, this.b.getContentResolver());
        return this.c;
    }

    public void a() {
        if (this.c != null) {
            try {
                a(this.c);
            } catch (Throwable e) {
                if (Log.isLoggable("LocalUriFetcher", 2)) {
                    Log.v("LocalUriFetcher", "failed to close data", e);
                }
            }
        }
    }

    public void c() {
    }

    public String b() {
        return this.a.toString();
    }
}
