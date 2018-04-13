package com.facebook.imagepipeline.c;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.facebook.imagepipeline.c.s.a;
import javax.annotation.Nullable;

class s$b {
    private final Context a;
    @Nullable
    private a b;

    private s$b(Context context) {
        this.a = context;
    }

    public synchronized SQLiteDatabase a() {
        if (this.b == null) {
            this.b = new a(this.a);
        }
        return this.b.getWritableDatabase();
    }
}
