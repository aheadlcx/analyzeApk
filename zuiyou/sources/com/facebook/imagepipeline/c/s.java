package com.facebook.imagepipeline.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import bolts.g;
import com.facebook.cache.common.c;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest$CacheChoice;
import com.facebook.imagepipeline.request.b;
import com.facebook.imagepipeline.request.b$a;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public class s implements r {
    private static final String a = s.class.getSimpleName();
    private static final String[] b = new String[]{"cache_choice", "cache_key", "width", "height"};
    @GuardedBy
    private final s$b c;
    private final Executor d;
    private final Executor e;

    private static class a extends SQLiteOpenHelper {
        public a(Context context) {
            super(context, "FrescoMediaVariationsIndex.db", null, 2);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.beginTransaction();
            try {
                sQLiteDatabase.execSQL("CREATE TABLE media_variations_index (_id INTEGER PRIMARY KEY,media_id TEXT,width INTEGER,height INTEGER,cache_choice TEXT,cache_key TEXT,resource_id TEXT UNIQUE )");
                sQLiteDatabase.execSQL("CREATE INDEX index_media_id ON media_variations_index (media_id)");
                sQLiteDatabase.setTransactionSuccessful();
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.beginTransaction();
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS media_variations_index");
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

    public s(Context context, Executor executor, Executor executor2) {
        this.c = new s$b(context, null);
        this.d = executor;
        this.e = executor2;
    }

    public g<b> a(final String str, final b$a b_a) {
        try {
            return g.a(new Callable<b>(this) {
                final /* synthetic */ s c;

                public /* synthetic */ Object call() throws Exception {
                    return a();
                }

                public b a() throws Exception {
                    return this.c.b(str, b_a);
                }
            }, this.d);
        } catch (Throwable e) {
            com.facebook.common.c.a.a(a, e, "Failed to schedule query task for %s", str);
            return g.a(e);
        }
    }

    protected b b(String str, b$a b_a) {
        b a;
        Throwable e;
        synchronized (s.class) {
            SQLiteDatabase a2 = this.c.a();
            Cursor query;
            try {
                String[] strArr = new String[]{str};
                query = a2.query("media_variations_index", b, "media_id = ?", strArr, null, null, null);
                try {
                    if (query.getCount() == 0) {
                        a = b_a.a();
                        if (query != null) {
                            query.close();
                        }
                    } else {
                        int columnIndexOrThrow = query.getColumnIndexOrThrow("cache_key");
                        int columnIndexOrThrow2 = query.getColumnIndexOrThrow("width");
                        int columnIndexOrThrow3 = query.getColumnIndexOrThrow("height");
                        int columnIndexOrThrow4 = query.getColumnIndexOrThrow("cache_choice");
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
                            b_a.a(parse, i, i2, imageRequest$CacheChoice);
                        }
                        a = b_a.a();
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
                    com.facebook.common.c.a.b(a, e, "Error reading for %s", str);
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
        return a;
    }

    public void a(String str, ImageRequest$CacheChoice imageRequest$CacheChoice, com.facebook.cache.common.b bVar, e eVar) {
        final String str2 = str;
        final ImageRequest$CacheChoice imageRequest$CacheChoice2 = imageRequest$CacheChoice;
        final com.facebook.cache.common.b bVar2 = bVar;
        final e eVar2 = eVar;
        this.e.execute(new Runnable(this) {
            final /* synthetic */ s e;

            public void run() {
                this.e.b(str2, imageRequest$CacheChoice2, bVar2, eVar2);
            }
        });
    }

    protected void b(String str, ImageRequest$CacheChoice imageRequest$CacheChoice, com.facebook.cache.common.b bVar, e eVar) {
        synchronized (s.class) {
            SQLiteDatabase a = this.c.a();
            try {
                a.beginTransaction();
                ContentValues contentValues = new ContentValues();
                contentValues.put("media_id", str);
                contentValues.put("width", Integer.valueOf(eVar.g()));
                contentValues.put("height", Integer.valueOf(eVar.h()));
                contentValues.put("cache_choice", imageRequest$CacheChoice.name());
                contentValues.put("cache_key", bVar.a());
                contentValues.put("resource_id", c.b(bVar));
                a.replaceOrThrow("media_variations_index", null, contentValues);
                a.setTransactionSuccessful();
                a.endTransaction();
            } catch (Throwable e) {
                com.facebook.common.c.a.b(a, e, "Error writing for %s", str);
                a.endTransaction();
            } catch (Throwable th) {
                a.endTransaction();
            }
        }
    }
}
