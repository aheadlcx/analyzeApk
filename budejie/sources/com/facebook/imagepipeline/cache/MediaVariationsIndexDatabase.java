package com.facebook.imagepipeline.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import bolts.g;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.CacheKeyUtil;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest$CacheChoice;
import com.facebook.imagepipeline.request.MediaVariations;
import com.facebook.imagepipeline.request.MediaVariations.Builder;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public class MediaVariationsIndexDatabase implements MediaVariationsIndex {
    private static final String[] PROJECTION = new String[]{IndexEntry.COLUMN_NAME_CACHE_CHOICE, IndexEntry.COLUMN_NAME_CACHE_KEY, IndexEntry.COLUMN_NAME_WIDTH, IndexEntry.COLUMN_NAME_HEIGHT};
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS media_variations_index";
    private static final String TAG = MediaVariationsIndexDatabase.class.getSimpleName();
    @GuardedBy("MediaVariationsIndexDatabase.class")
    private final MediaVariationsIndexDatabase$LazyIndexDbOpenHelper mDbHelper;
    private final Executor mReadExecutor;
    private final Executor mWriteExecutor;

    private static class IndexDbOpenHelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "FrescoMediaVariationsIndex.db";
        public static final int DATABASE_VERSION = 2;
        private static final String INTEGER_TYPE = " INTEGER";
        private static final String SQL_CREATE_ENTRIES = "CREATE TABLE media_variations_index (_id INTEGER PRIMARY KEY,media_id TEXT,width INTEGER,height INTEGER,cache_choice TEXT,cache_key TEXT,resource_id TEXT UNIQUE )";
        private static final String SQL_CREATE_INDEX = "CREATE INDEX index_media_id ON media_variations_index (media_id)";
        private static final String TEXT_TYPE = " TEXT";

        public IndexDbOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, 2);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.beginTransaction();
            try {
                sQLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
                sQLiteDatabase.execSQL(SQL_CREATE_INDEX);
                sQLiteDatabase.setTransactionSuccessful();
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.beginTransaction();
            try {
                sQLiteDatabase.execSQL(MediaVariationsIndexDatabase.SQL_DELETE_ENTRIES);
                sQLiteDatabase.setTransactionSuccessful();
                onCreate(sQLiteDatabase);
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            onUpgrade(sQLiteDatabase, i, i2);
        }
    }

    private static final class IndexEntry implements BaseColumns {
        public static final String COLUMN_NAME_CACHE_CHOICE = "cache_choice";
        public static final String COLUMN_NAME_CACHE_KEY = "cache_key";
        public static final String COLUMN_NAME_HEIGHT = "height";
        public static final String COLUMN_NAME_MEDIA_ID = "media_id";
        public static final String COLUMN_NAME_RESOURCE_ID = "resource_id";
        public static final String COLUMN_NAME_WIDTH = "width";
        public static final String TABLE_NAME = "media_variations_index";

        private IndexEntry() {
        }
    }

    public MediaVariationsIndexDatabase(Context context, Executor executor, Executor executor2) {
        this.mDbHelper = new MediaVariationsIndexDatabase$LazyIndexDbOpenHelper(context, null);
        this.mReadExecutor = executor;
        this.mWriteExecutor = executor2;
    }

    public g<MediaVariations> getCachedVariants(final String str, final Builder builder) {
        try {
            return g.a(new Callable<MediaVariations>() {
                public MediaVariations call() throws Exception {
                    return MediaVariationsIndexDatabase.this.getCachedVariantsSync(str, builder);
                }
            }, this.mReadExecutor);
        } catch (Exception e) {
            FLog.w(TAG, (Throwable) e, "Failed to schedule query task for %s", str);
            return g.a(e);
        }
    }

    @VisibleForTesting
    protected MediaVariations getCachedVariantsSync(String str, Builder builder) {
        MediaVariations build;
        Throwable e;
        synchronized (MediaVariationsIndexDatabase.class) {
            SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
            Cursor query;
            try {
                String[] strArr = new String[]{str};
                query = writableDatabase.query(IndexEntry.TABLE_NAME, PROJECTION, "media_id = ?", strArr, null, null, null);
                try {
                    if (query.getCount() == 0) {
                        build = builder.build();
                        if (query != null) {
                            query.close();
                        }
                    } else {
                        int columnIndexOrThrow = query.getColumnIndexOrThrow(IndexEntry.COLUMN_NAME_CACHE_KEY);
                        int columnIndexOrThrow2 = query.getColumnIndexOrThrow(IndexEntry.COLUMN_NAME_WIDTH);
                        int columnIndexOrThrow3 = query.getColumnIndexOrThrow(IndexEntry.COLUMN_NAME_HEIGHT);
                        int columnIndexOrThrow4 = query.getColumnIndexOrThrow(IndexEntry.COLUMN_NAME_CACHE_CHOICE);
                        while (query.moveToNext()) {
                            ImageRequest$CacheChoice imageRequest$CacheChoice;
                            Object string = query.getString(columnIndexOrThrow4);
                            Uri parse = Uri.parse(query.getString(columnIndexOrThrow));
                            int i = query.getInt(columnIndexOrThrow2);
                            int i2 = query.getInt(columnIndexOrThrow3);
                            if (TextUtils.isEmpty(string)) {
                                imageRequest$CacheChoice = null;
                            } else {
                                imageRequest$CacheChoice = ImageRequest$CacheChoice.valueOf(string);
                            }
                            builder.addVariant(parse, i, i2, imageRequest$CacheChoice);
                        }
                        build = builder.build();
                        if (query != null) {
                            query.close();
                        }
                    }
                } catch (SQLException e2) {
                    e = e2;
                }
            } catch (SQLException e3) {
                e = e3;
                query = null;
                try {
                    FLog.e(TAG, e, "Error reading for %s", str);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    if (query != null) {
                        query.close();
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw e;
            }
        }
        return build;
    }

    public void saveCachedVariant(String str, ImageRequest$CacheChoice imageRequest$CacheChoice, CacheKey cacheKey, EncodedImage encodedImage) {
        final String str2 = str;
        final ImageRequest$CacheChoice imageRequest$CacheChoice2 = imageRequest$CacheChoice;
        final CacheKey cacheKey2 = cacheKey;
        final EncodedImage encodedImage2 = encodedImage;
        this.mWriteExecutor.execute(new Runnable() {
            public void run() {
                MediaVariationsIndexDatabase.this.saveCachedVariantSync(str2, imageRequest$CacheChoice2, cacheKey2, encodedImage2);
            }
        });
    }

    protected void saveCachedVariantSync(String str, ImageRequest$CacheChoice imageRequest$CacheChoice, CacheKey cacheKey, EncodedImage encodedImage) {
        synchronized (MediaVariationsIndexDatabase.class) {
            SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                ContentValues contentValues = new ContentValues();
                contentValues.put(IndexEntry.COLUMN_NAME_MEDIA_ID, str);
                contentValues.put(IndexEntry.COLUMN_NAME_WIDTH, Integer.valueOf(encodedImage.getWidth()));
                contentValues.put(IndexEntry.COLUMN_NAME_HEIGHT, Integer.valueOf(encodedImage.getHeight()));
                contentValues.put(IndexEntry.COLUMN_NAME_CACHE_CHOICE, imageRequest$CacheChoice.name());
                contentValues.put(IndexEntry.COLUMN_NAME_CACHE_KEY, cacheKey.getUriString());
                contentValues.put(IndexEntry.COLUMN_NAME_RESOURCE_ID, CacheKeyUtil.getFirstResourceId(cacheKey));
                writableDatabase.replaceOrThrow(IndexEntry.TABLE_NAME, null, contentValues);
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            } catch (Throwable e) {
                FLog.e(TAG, e, "Error writing for %s", str);
                writableDatabase.endTransaction();
            } catch (Throwable th) {
                writableDatabase.endTransaction();
            }
        }
    }
}
