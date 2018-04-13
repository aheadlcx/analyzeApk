package com.facebook.imagepipeline.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexDbOpenHelper;
import javax.annotation.Nullable;

class MediaVariationsIndexDatabase$LazyIndexDbOpenHelper {
    private final Context mContext;
    @Nullable
    private IndexDbOpenHelper mIndexDbOpenHelper;

    private MediaVariationsIndexDatabase$LazyIndexDbOpenHelper(Context context) {
        this.mContext = context;
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        if (this.mIndexDbOpenHelper == null) {
            this.mIndexDbOpenHelper = new IndexDbOpenHelper(this.mContext);
        }
        return this.mIndexDbOpenHelper.getWritableDatabase();
    }
}
