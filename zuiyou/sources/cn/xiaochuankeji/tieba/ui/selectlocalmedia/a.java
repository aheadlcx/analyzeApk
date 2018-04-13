package cn.xiaochuankeji.tieba.ui.selectlocalmedia;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class a extends AsyncTask<Void, Void, Void> {
    private final ArrayList<LocalMedia> a = new ArrayList();
    private final ArrayList<a> b = new ArrayList();
    private ContentResolver c;
    private b d;
    private boolean e;

    public interface b {
        void a(ArrayList<LocalMedia> arrayList, ArrayList<a> arrayList2);
    }

    public static class a {
        int a;
        int b;
        String c;
        int d;
        String e;
        int f;

        boolean a() {
            return this.c != null && this.e != null && this.c.equals("Camera") && this.e.contains("/DCIM/Camera/");
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Void) obj);
    }

    public a(ContentResolver contentResolver, b bVar, boolean z) {
        this.c = contentResolver;
        this.d = bVar;
        this.e = z;
    }

    protected Void a(Void... voidArr) {
        a();
        if (!this.e) {
            b();
        }
        if (!isCancelled()) {
            c();
        }
        return null;
    }

    private void a() {
        Cursor query = this.c.query(Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "date_added", "bucket_id", "bucket_display_name", "width", "height", "_size", "mime_type", "orientation"}, null, null, null);
        if (query != null && query.moveToFirst()) {
            int columnIndex = query.getColumnIndex("_id");
            int columnIndex2 = query.getColumnIndex("_data");
            int columnIndex3 = query.getColumnIndex("date_added");
            int columnIndex4 = query.getColumnIndex("bucket_id");
            int columnIndex5 = query.getColumnIndex("bucket_display_name");
            int columnIndex6 = query.getColumnIndex("width");
            int columnIndex7 = query.getColumnIndex("height");
            int columnIndex8 = query.getColumnIndex("_size");
            int columnIndex9 = query.getColumnIndex("mime_type");
            int columnIndex10 = query.getColumnIndex("orientation");
            do {
                int i = query.getInt(columnIndex4);
                LocalMedia localMedia = new LocalMedia();
                localMedia.mediaID = query.getInt(columnIndex);
                localMedia.bucketID = i;
                localMedia.path = query.getString(columnIndex2);
                localMedia.type = 2;
                localMedia.width = query.getInt(columnIndex6);
                localMedia.height = query.getInt(columnIndex7);
                localMedia.rotate = query.getInt(columnIndex10);
                localMedia.size = (long) query.getInt(columnIndex8);
                localMedia.mimeType = query.getString(columnIndex9);
                if (!TextUtils.isEmpty(localMedia.path)) {
                    File file = new File(localMedia.path);
                    if (!(!file.exists() || file.length() == 0 || localMedia.path.endsWith("webp") || String.valueOf(localMedia.mimeType).toLowerCase().contains("webp"))) {
                        a aVar;
                        localMedia.dateAdded = query.getLong(columnIndex3);
                        this.a.add(localMedia);
                        Iterator it = this.b.iterator();
                        while (it.hasNext()) {
                            aVar = (a) it.next();
                            if (aVar.a == i) {
                                break;
                            }
                        }
                        aVar = null;
                        if (aVar == null) {
                            aVar = new a();
                            aVar.a = i;
                            aVar.c = query.getString(columnIndex5);
                            this.b.add(aVar);
                        }
                        aVar.b++;
                        aVar.d = localMedia.mediaID;
                        aVar.e = localMedia.path;
                        aVar.f = localMedia.type;
                    }
                }
                if (isCancelled()) {
                    break;
                }
            } while (query.moveToNext());
        }
        if (query != null) {
            query.close();
        }
    }

    private void b() {
        Cursor query = this.c.query(Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "date_added", "bucket_id", "bucket_display_name", "_size", "mime_type", "width", "height", "duration"}, null, null, null);
        if (query != null && query.moveToFirst()) {
            int columnIndex = query.getColumnIndex("_id");
            int columnIndex2 = query.getColumnIndex("_data");
            int columnIndex3 = query.getColumnIndex("date_added");
            int columnIndex4 = query.getColumnIndex("bucket_id");
            int columnIndex5 = query.getColumnIndex("bucket_display_name");
            int columnIndex6 = query.getColumnIndex("_size");
            int columnIndex7 = query.getColumnIndex("mime_type");
            int columnIndex8 = query.getColumnIndex("width");
            int columnIndex9 = query.getColumnIndex("height");
            do {
                int i = query.getInt(columnIndex4);
                LocalMedia localMedia = new LocalMedia();
                localMedia.mediaID = query.getInt(columnIndex);
                localMedia.bucketID = i;
                localMedia.path = query.getString(columnIndex2);
                localMedia.size = query.getLong(columnIndex6);
                localMedia.mimeType = query.getString(columnIndex7);
                localMedia.type = 1;
                localMedia.width = query.getInt(columnIndex8);
                localMedia.height = query.getInt(columnIndex9);
                localMedia.duration = query.getLong(query.getColumnIndex("duration"));
                if (!TextUtils.isEmpty(localMedia.path) && new File(localMedia.path).exists()) {
                    a aVar;
                    localMedia.dateAdded = query.getLong(columnIndex3);
                    this.a.add(localMedia);
                    Iterator it = this.b.iterator();
                    while (it.hasNext()) {
                        aVar = (a) it.next();
                        if (aVar.a == i) {
                            break;
                        }
                    }
                    aVar = null;
                    if (aVar == null) {
                        aVar = new a();
                        aVar.a = i;
                        aVar.c = query.getString(columnIndex5);
                        this.b.add(aVar);
                    }
                    aVar.b++;
                    aVar.d = localMedia.mediaID;
                    aVar.e = localMedia.path;
                    aVar.f = localMedia.type;
                }
                if (isCancelled()) {
                    break;
                }
            } while (query.moveToNext());
        }
        if (query != null) {
            query.close();
        }
    }

    protected void a(Void voidR) {
        if (!isCancelled()) {
            this.d.a(this.a, this.b);
        }
    }

    private void c() {
        Collections.sort(this.a, new Comparator<LocalMedia>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((LocalMedia) obj, (LocalMedia) obj2);
            }

            public int a(LocalMedia localMedia, LocalMedia localMedia2) {
                if (localMedia.dateAdded == localMedia2.dateAdded) {
                    return 0;
                }
                return localMedia.dateAdded < localMedia2.dateAdded ? 1 : -1;
            }
        });
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.a()) {
                aVar.c = "相机";
                this.b.remove(aVar);
                this.b.add(0, aVar);
                return;
            }
        }
    }
}
