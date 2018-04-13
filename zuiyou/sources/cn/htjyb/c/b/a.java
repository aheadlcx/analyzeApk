package cn.htjyb.c.b;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Video.Thumbnails;
import android.util.SparseArray;
import java.io.File;
import java.util.HashMap;

public class a extends cn.htjyb.c.c {
    private boolean a;
    private ContentResolver b;
    private HashMap<Object, d> c = new HashMap();
    private Options d;
    private SparseArray<String> e = new SparseArray();
    private SparseArray<String> f = new SparseArray();
    private final b g = new b();
    private d h;

    public interface a {
        void a(Object obj, Bitmap bitmap);
    }

    private class b extends Handler {
        final /* synthetic */ a a;

        private b(a aVar) {
            this.a = aVar;
        }

        public void handleMessage(Message message) {
            if (!this.a.a) {
                d dVar = (d) message.obj;
                this.a.c.remove(dVar.a);
                if (dVar.f != null) {
                    dVar.f.a(dVar.a, dVar.e);
                }
            }
        }
    }

    private class c implements Runnable {
        d a;
        final /* synthetic */ a b;

        c(a aVar, d dVar) {
            this.b = aVar;
            this.a = dVar;
        }

        public void run() {
            if (this.a == this.b.h) {
                com.izuiyou.a.a.b.c("cancelThumbnailRequest mediaID: " + this.a.c);
                if (this.a.b) {
                    Thumbnails.cancelThumbnailRequest(this.b.b, (long) this.a.c);
                } else {
                    Images.Thumbnails.cancelThumbnailRequest(this.b.b, (long) this.a.c);
                }
            }
        }
    }

    private static class d {
        Object a;
        boolean b;
        int c;
        String d;
        Bitmap e;
        a f;

        private d() {
        }
    }

    public a(ContentResolver contentResolver) {
        this.b = contentResolver;
        this.d = new Options();
        this.d.inPreferredConfig = Config.RGB_565;
    }

    public void a(Object obj, boolean z, int i, String str, a aVar) {
        d dVar = new d();
        dVar.a = obj;
        dVar.b = z;
        dVar.c = i;
        dVar.d = str;
        dVar.f = aVar;
        this.c.put(obj, dVar);
        Message obtain = Message.obtain();
        obtain.obj = dVar;
        a(obtain);
    }

    public void a(Object obj) {
        if (obj != null) {
            d dVar = (d) this.c.remove(obj);
            if (dVar != null) {
                dVar.f = null;
            }
        }
    }

    public void a() {
        super.a();
        this.a = true;
        this.c.clear();
    }

    public void run() {
        a(this.e, Images.Thumbnails.EXTERNAL_CONTENT_URI, "image_id", "_data", false);
        a(this.f, Thumbnails.EXTERNAL_CONTENT_URI, "video_id", "_data", true);
        if (!this.a) {
            super.run();
        }
    }

    @TargetApi(8)
    protected void b(Message message) {
        if (!this.a) {
            d dVar = (d) message.obj;
            if (dVar.f == null) {
                com.izuiyou.a.a.b.c("canceled load, mediaID: " + dVar.c);
                return;
            }
            Bitmap a;
            this.h = dVar;
            this.g.postDelayed(new c(this, dVar), 500);
            String str;
            if (dVar.b) {
                str = (String) this.e.get(dVar.c);
                if (str == null || !new File(str).exists()) {
                    str = dVar.d;
                }
                a = b.a(str, 200.0f);
            } else {
                str = (String) this.f.get(dVar.c);
                if (str != null) {
                    a = b.a(str, 200.0f);
                } else {
                    a = null;
                }
                if (a == null) {
                    a = ThumbnailUtils.createVideoThumbnail(dVar.d, 1);
                }
            }
            this.h = null;
            if (a == null) {
                com.izuiyou.a.a.b.e("getThumbnail failed, mediaID: " + dVar.c);
                return;
            }
            dVar.e = a;
            this.g.obtainMessage(0, dVar).sendToTarget();
        }
    }

    private void a(SparseArray<String> sparseArray, Uri uri, String str, String str2, boolean z) {
        String[] strArr = new String[]{str, str2};
        Cursor query = this.b.query(uri, strArr, (z ? "video_id" : "image_id") + "=?", new String[]{"" + str}, null);
        if (query != null) {
            if (query.moveToFirst()) {
                int columnIndex = query.getColumnIndex(str);
                int columnIndex2 = query.getColumnIndex(str2);
                do {
                    sparseArray.put(query.getInt(columnIndex), query.getString(columnIndex2));
                    if (this.a) {
                        break;
                    }
                } while (query.moveToNext());
            }
            query.close();
        }
    }
}
