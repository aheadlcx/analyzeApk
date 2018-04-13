package cn.xiaochuankeji.tieba.network.filedownload;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import com.iflytek.aiui.AIUIConstant;
import com.liulishuo.filedownloader.g.d;
import com.liulishuo.filedownloader.g.f;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import tv.danmaku.ijk.media.player.FFmpegMediaMetadataRetriever;

public class c implements com.liulishuo.filedownloader.g.c.c {

    class a implements com.liulishuo.filedownloader.b.a {
        final /* synthetic */ c a;
        private final SparseArray<com.liulishuo.filedownloader.d.c> b = new SparseArray();
        private SQLiteDatabase c = new b(com.liulishuo.filedownloader.g.c.a()).getWritableDatabase();

        class a implements com.liulishuo.filedownloader.b.a.a {
            final /* synthetic */ a a;
            private final SparseArray<com.liulishuo.filedownloader.d.c> b = new SparseArray();
            private b c;

            a(a aVar) {
                this.a = aVar;
            }

            @NonNull
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
                    this.a.c.beginTransaction();
                    int i = 0;
                    while (i < size) {
                        try {
                            int keyAt = this.b.keyAt(i);
                            com.liulishuo.filedownloader.d.c cVar = (com.liulishuo.filedownloader.d.c) this.b.get(keyAt);
                            this.a.c.delete("filedownloader", "_id = ?", new String[]{String.valueOf(keyAt)});
                            this.a.c.insert("filedownloader", null, cVar.p());
                            if (cVar.n() > 1) {
                                List<com.liulishuo.filedownloader.d.a> c = this.a.c(keyAt);
                                if (c.size() > 0) {
                                    this.a.c.delete("filedownloaderConnection", "id = ?", new String[]{String.valueOf(keyAt)});
                                    for (com.liulishuo.filedownloader.d.a aVar : c) {
                                        aVar.a(cVar.a());
                                        this.a.c.insert("filedownloaderConnection", null, aVar.f());
                                    }
                                }
                            }
                            i++;
                        } catch (Throwable th) {
                            this.a.c.endTransaction();
                        }
                    }
                    this.a.c.setTransactionSuccessful();
                    this.a.c.endTransaction();
                }
            }

            public void a(com.liulishuo.filedownloader.d.c cVar) {
            }

            public void b(com.liulishuo.filedownloader.d.c cVar) {
                this.a.b.put(cVar.a(), cVar);
            }

            public void a(int i, com.liulishuo.filedownloader.d.c cVar) {
                this.b.put(i, cVar);
            }
        }

        class b implements Iterator<com.liulishuo.filedownloader.d.c> {
            final /* synthetic */ a a;
            private final Cursor b;
            private final List<Integer> c = new ArrayList();
            private int d;

            public /* synthetic */ Object next() {
                return a();
            }

            b(a aVar) {
                this.a = aVar;
                this.b = aVar.c.rawQuery("SELECT * FROM filedownloader", null);
            }

            public boolean hasNext() {
                return this.b.moveToNext();
            }

            public com.liulishuo.filedownloader.d.c a() {
                boolean z = true;
                com.liulishuo.filedownloader.d.c cVar = new com.liulishuo.filedownloader.d.c();
                cVar.a(this.b.getInt(this.b.getColumnIndex("_id")));
                cVar.a(this.b.getString(this.b.getColumnIndex("url")));
                String string = this.b.getString(this.b.getColumnIndex(AIUIConstant.RES_TYPE_PATH));
                if (this.b.getShort(this.b.getColumnIndex("pathAsDirectory")) != (short) 1) {
                    z = false;
                }
                cVar.a(string, z);
                cVar.a((byte) this.b.getShort(this.b.getColumnIndex(NotificationCompat.CATEGORY_STATUS)));
                cVar.a(this.b.getLong(this.b.getColumnIndex("sofar")));
                cVar.c(this.b.getLong(this.b.getColumnIndex("total")));
                cVar.c(this.b.getString(this.b.getColumnIndex("errMsg")));
                cVar.b(this.b.getString(this.b.getColumnIndex("etag")));
                cVar.d(this.b.getString(this.b.getColumnIndex(FFmpegMediaMetadataRetriever.METADATA_KEY_FILENAME)));
                cVar.b(this.b.getInt(this.b.getColumnIndex("connectionCount")));
                this.d = cVar.a();
                return cVar;
            }

            public void remove() {
                this.c.add(Integer.valueOf(this.d));
            }

            void b() {
                this.b.close();
                if (!this.c.isEmpty()) {
                    String join = TextUtils.join(", ", this.c);
                    if (d.a) {
                        d.c(this, "delete %s", new Object[]{join});
                    }
                    this.a.c.execSQL(f.a("DELETE FROM %s WHERE %s IN (%s);", new Object[]{"filedownloader", "_id", join}));
                    this.a.c.execSQL(f.a("DELETE FROM %s WHERE %s IN (%s);", new Object[]{"filedownloaderConnection", "id", join}));
                }
            }
        }

        a(c cVar) {
            this.a = cVar;
        }

        public void a(int i) {
        }

        public com.liulishuo.filedownloader.d.c b(int i) {
            return (com.liulishuo.filedownloader.d.c) this.b.get(i);
        }

        public List<com.liulishuo.filedownloader.d.a> c(int i) {
            List<com.liulishuo.filedownloader.d.a> arrayList = new ArrayList();
            Cursor cursor = null;
            try {
                cursor = this.c.rawQuery(f.a("SELECT * FROM %s WHERE %s = ?", new Object[]{"filedownloaderConnection", "id"}), new String[]{Integer.toString(i)});
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
            this.c.execSQL("DELETE FROM filedownloaderConnection WHERE id = " + i);
        }

        public void a(com.liulishuo.filedownloader.d.a aVar) {
            this.c.insert("filedownloaderConnection", null, aVar.f());
        }

        public void a(int i, int i2, long j) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("currentOffset", Long.valueOf(j));
            this.c.update("filedownloaderConnection", contentValues, "id = ? AND connectionIndex = ?", new String[]{Integer.toString(i), Integer.toString(i2)});
        }

        public void a(int i, int i2) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("connectionCount", Integer.valueOf(i2));
            this.c.update("filedownloader", contentValues, "_id = ? ", new String[]{Integer.toString(i)});
        }

        public void a(com.liulishuo.filedownloader.d.c cVar) {
            this.b.put(cVar.a(), cVar);
            this.c.insert("filedownloader", null, cVar.p());
        }

        public void b(com.liulishuo.filedownloader.d.c cVar) {
            if (cVar == null) {
                d.d(this, "update but model == null!", new Object[0]);
            } else if (b(cVar.a()) != null) {
                this.b.remove(cVar.a());
                this.b.put(cVar.a(), cVar);
                this.c.update("filedownloader", cVar.p(), "_id = ? ", new String[]{String.valueOf(cVar.a())});
            } else {
                a(cVar);
            }
        }

        public boolean e(int i) {
            this.b.remove(i);
            if (this.c.delete("filedownloader", "_id = ?", new String[]{String.valueOf(i)}) != 0) {
                return true;
            }
            return false;
        }

        public void a() {
            this.b.clear();
            this.c.delete("filedownloader", null, null);
            this.c.delete("filedownloaderConnection", null, null);
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

        private void a(int i, ContentValues contentValues) {
            this.c.update("filedownloader", contentValues, "_id = ? ", new String[]{String.valueOf(i)});
        }
    }

    public com.liulishuo.filedownloader.b.a a() {
        return new a(this);
    }
}
