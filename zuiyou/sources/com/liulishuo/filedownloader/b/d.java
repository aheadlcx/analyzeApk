package com.liulishuo.filedownloader.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import com.iflytek.aiui.AIUIConstant;
import com.liulishuo.filedownloader.g.c;
import com.liulishuo.filedownloader.g.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import tv.danmaku.ijk.media.player.FFmpegMediaMetadataRetriever;

public class d implements a {
    private final SQLiteDatabase a = new e(c.a()).getWritableDatabase();

    public class a implements com.liulishuo.filedownloader.b.a.a {
        final /* synthetic */ d a;
        private final SparseArray<com.liulishuo.filedownloader.d.c> b;
        private b c;
        private final SparseArray<com.liulishuo.filedownloader.d.c> d;
        private final SparseArray<List<com.liulishuo.filedownloader.d.a>> e;

        a(d dVar) {
            this(dVar, null, null);
        }

        a(d dVar, SparseArray<com.liulishuo.filedownloader.d.c> sparseArray, SparseArray<List<com.liulishuo.filedownloader.d.a>> sparseArray2) {
            this.a = dVar;
            this.b = new SparseArray();
            this.d = sparseArray;
            this.e = sparseArray2;
        }

        public Iterator<com.liulishuo.filedownloader.d.c> iterator() {
            Iterator bVar = new b(this.a);
            this.c = bVar;
            return bVar;
        }

        public void a() {
            if (this.c != null) {
                this.c.b();
            }
            int size = this.b.size();
            if (size >= 0) {
                int keyAt;
                this.a.a.beginTransaction();
                int i = 0;
                while (i < size) {
                    try {
                        keyAt = this.b.keyAt(i);
                        com.liulishuo.filedownloader.d.c cVar = (com.liulishuo.filedownloader.d.c) this.b.get(keyAt);
                        this.a.a.delete("filedownloader", "_id = ?", new String[]{String.valueOf(keyAt)});
                        this.a.a.insert("filedownloader", null, cVar.p());
                        if (cVar.n() > 1) {
                            List<com.liulishuo.filedownloader.d.a> c = this.a.c(keyAt);
                            if (c.size() > 0) {
                                this.a.a.delete("filedownloaderConnection", "id = ?", new String[]{String.valueOf(keyAt)});
                                for (com.liulishuo.filedownloader.d.a aVar : c) {
                                    aVar.a(cVar.a());
                                    this.a.a.insert("filedownloaderConnection", null, aVar.f());
                                }
                            }
                        }
                        i++;
                    } catch (Throwable th) {
                        this.a.a.endTransaction();
                    }
                }
                if (!(this.d == null || this.e == null)) {
                    i = this.d.size();
                    for (keyAt = 0; keyAt < i; keyAt++) {
                        int a = ((com.liulishuo.filedownloader.d.c) this.d.valueAt(keyAt)).a();
                        List c2 = this.a.c(a);
                        if (c2 != null && c2.size() > 0) {
                            this.e.put(a, c2);
                        }
                    }
                }
                this.a.a.setTransactionSuccessful();
                this.a.a.endTransaction();
            }
        }

        public void a(com.liulishuo.filedownloader.d.c cVar) {
        }

        public void b(com.liulishuo.filedownloader.d.c cVar) {
            if (this.d != null) {
                this.d.put(cVar.a(), cVar);
            }
        }

        public void a(int i, com.liulishuo.filedownloader.d.c cVar) {
            this.b.put(i, cVar);
        }
    }

    class b implements Iterator<com.liulishuo.filedownloader.d.c> {
        final /* synthetic */ d a;
        private final Cursor b;
        private final List<Integer> c = new ArrayList();
        private int d;

        public /* synthetic */ Object next() {
            return a();
        }

        b(d dVar) {
            this.a = dVar;
            this.b = dVar.a.rawQuery("SELECT * FROM filedownloader", null);
        }

        public boolean hasNext() {
            return this.b.moveToNext();
        }

        public com.liulishuo.filedownloader.d.c a() {
            com.liulishuo.filedownloader.d.c a = d.b(this.b);
            this.d = a.a();
            return a;
        }

        public void remove() {
            this.c.add(Integer.valueOf(this.d));
        }

        void b() {
            this.b.close();
            if (!this.c.isEmpty()) {
                String join = TextUtils.join(", ", this.c);
                if (com.liulishuo.filedownloader.g.d.a) {
                    com.liulishuo.filedownloader.g.d.c(this, "delete %s", join);
                }
                this.a.a.execSQL(f.a("DELETE FROM %s WHERE %s IN (%s);", "filedownloader", "_id", join));
                this.a.a.execSQL(f.a("DELETE FROM %s WHERE %s IN (%s);", "filedownloaderConnection", "id", join));
            }
        }
    }

    public void a(int i) {
    }

    public com.liulishuo.filedownloader.d.c b(int i) {
        Cursor rawQuery;
        Throwable th;
        com.liulishuo.filedownloader.d.c cVar = null;
        try {
            rawQuery = this.a.rawQuery(f.a("SELECT * FROM %s WHERE %s = ?", "filedownloader", "_id"), new String[]{Integer.toString(i)});
            try {
                if (rawQuery.moveToNext()) {
                    cVar = b(rawQuery);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                } else if (rawQuery != null) {
                    rawQuery.close();
                }
                return cVar;
            } catch (Throwable th2) {
                th = th2;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            rawQuery = null;
            th = th4;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    public List<com.liulishuo.filedownloader.d.a> c(int i) {
        List<com.liulishuo.filedownloader.d.a> arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = this.a.rawQuery(f.a("SELECT * FROM %s WHERE %s = ?", "filedownloaderConnection", "id"), new String[]{Integer.toString(i)});
            while (cursor.moveToNext()) {
                com.liulishuo.filedownloader.d.a aVar = new com.liulishuo.filedownloader.d.a();
                aVar.a(i);
                aVar.b(cursor.getInt(cursor.getColumnIndex("connectionIndex")));
                aVar.a(cursor.getLong(cursor.getColumnIndex("startOffset")));
                aVar.b(cursor.getLong(cursor.getColumnIndex("currentOffset")));
                aVar.c(cursor.getLong(cursor.getColumnIndex("endOffset")));
                arrayList.add(aVar);
            }
            return arrayList;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void d(int i) {
        this.a.execSQL("DELETE FROM filedownloaderConnection WHERE id = " + i);
    }

    public void a(com.liulishuo.filedownloader.d.a aVar) {
        this.a.insert("filedownloaderConnection", null, aVar.f());
    }

    public void a(int i, int i2, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("currentOffset", Long.valueOf(j));
        this.a.update("filedownloaderConnection", contentValues, "id = ? AND connectionIndex = ?", new String[]{Integer.toString(i), Integer.toString(i2)});
    }

    public void a(int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("connectionCount", Integer.valueOf(i2));
        this.a.update("filedownloader", contentValues, "_id = ? ", new String[]{Integer.toString(i)});
    }

    public void a(com.liulishuo.filedownloader.d.c cVar) {
        this.a.insert("filedownloader", null, cVar.p());
    }

    public void b(com.liulishuo.filedownloader.d.c cVar) {
        if (cVar == null) {
            com.liulishuo.filedownloader.g.d.d(this, "update but model == null!", new Object[0]);
        } else if (b(cVar.a()) != null) {
            this.a.update("filedownloader", cVar.p(), "_id = ? ", new String[]{String.valueOf(cVar.a())});
        } else {
            a(cVar);
        }
    }

    public boolean e(int i) {
        if (this.a.delete("filedownloader", "_id = ?", new String[]{String.valueOf(i)}) != 0) {
            return true;
        }
        return false;
    }

    public void a() {
        this.a.delete("filedownloader", null, null);
        this.a.delete("filedownloaderConnection", null, null);
    }

    public void a(int i, String str, long j, long j2, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sofar", Long.valueOf(j));
        contentValues.put("total", Long.valueOf(j2));
        contentValues.put("etag", str);
        contentValues.put("connectionCount", Integer.valueOf(i2));
        a(i, contentValues);
    }

    public void a(int i, long j, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotificationCompat.CATEGORY_STATUS, Byte.valueOf((byte) 2));
        contentValues.put("total", Long.valueOf(j));
        contentValues.put("etag", str);
        contentValues.put(FFmpegMediaMetadataRetriever.METADATA_KEY_FILENAME, str2);
        a(i, contentValues);
    }

    public void a(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotificationCompat.CATEGORY_STATUS, Byte.valueOf((byte) 3));
        contentValues.put("sofar", Long.valueOf(j));
        a(i, contentValues);
    }

    public void a(int i, Throwable th, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("errMsg", th.toString());
        contentValues.put(NotificationCompat.CATEGORY_STATUS, Byte.valueOf((byte) -1));
        contentValues.put("sofar", Long.valueOf(j));
        a(i, contentValues);
    }

    public void a(int i, Throwable th) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("errMsg", th.toString());
        contentValues.put(NotificationCompat.CATEGORY_STATUS, Byte.valueOf((byte) 5));
        a(i, contentValues);
    }

    public void b(int i, long j) {
        e(i);
    }

    public void c(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotificationCompat.CATEGORY_STATUS, Byte.valueOf((byte) -2));
        contentValues.put("sofar", Long.valueOf(j));
        a(i, contentValues);
    }

    public void f(int i) {
    }

    public com.liulishuo.filedownloader.b.a.a b() {
        return new a(this);
    }

    public com.liulishuo.filedownloader.b.a.a a(SparseArray<com.liulishuo.filedownloader.d.c> sparseArray, SparseArray<List<com.liulishuo.filedownloader.d.a>> sparseArray2) {
        return new a(this, sparseArray, sparseArray2);
    }

    private void a(int i, ContentValues contentValues) {
        this.a.update("filedownloader", contentValues, "_id = ? ", new String[]{String.valueOf(i)});
    }

    private static com.liulishuo.filedownloader.d.c b(Cursor cursor) {
        boolean z = true;
        com.liulishuo.filedownloader.d.c cVar = new com.liulishuo.filedownloader.d.c();
        cVar.a(cursor.getInt(cursor.getColumnIndex("_id")));
        cVar.a(cursor.getString(cursor.getColumnIndex("url")));
        String string = cursor.getString(cursor.getColumnIndex(AIUIConstant.RES_TYPE_PATH));
        if (cursor.getShort(cursor.getColumnIndex("pathAsDirectory")) != (short) 1) {
            z = false;
        }
        cVar.a(string, z);
        cVar.a((byte) cursor.getShort(cursor.getColumnIndex(NotificationCompat.CATEGORY_STATUS)));
        cVar.a(cursor.getLong(cursor.getColumnIndex("sofar")));
        cVar.c(cursor.getLong(cursor.getColumnIndex("total")));
        cVar.c(cursor.getString(cursor.getColumnIndex("errMsg")));
        cVar.b(cursor.getString(cursor.getColumnIndex("etag")));
        cVar.d(cursor.getString(cursor.getColumnIndex(FFmpegMediaMetadataRetriever.METADATA_KEY_FILENAME)));
        cVar.b(cursor.getInt(cursor.getColumnIndex("connectionCount")));
        return cVar;
    }
}
