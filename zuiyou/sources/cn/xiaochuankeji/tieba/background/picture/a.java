package cn.xiaochuankeji.tieba.background.picture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.SparseArray;
import cn.htjyb.c.d;
import cn.htjyb.netlib.e;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class a extends e {
    private final ReferenceQueue<PictureImpl> a = new ReferenceQueue();
    private final HashMap<String, WeakReference<PictureImpl>> b = new HashMap();
    private final ReferenceQueue<Bitmap> c = new ReferenceQueue();
    private final HashMap<String, WeakReference<Bitmap>> d = new HashMap();
    private final SparseArray<SoftReference<Bitmap>> e = new SparseArray();

    public a() {
        super(2);
    }

    private static String a(Type type, String str) {
        return Integer.toString(type.ordinal()) + str;
    }

    private static void a(ReferenceQueue referenceQueue, HashMap hashMap) {
        if (referenceQueue.poll() != null) {
            do {
            } while (referenceQueue.poll() != null);
            ArrayList arrayList = new ArrayList();
            for (Entry entry : hashMap.entrySet()) {
                if (((Reference) entry.getValue()).get() == null) {
                    arrayList.add(entry.getKey());
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                hashMap.remove(it.next());
            }
        }
    }

    public PictureImpl a(Type type, long j) {
        a(this.a, this.b);
        String a = a(type, Long.toString(j));
        Reference reference = (Reference) this.b.get(a);
        PictureImpl pictureImpl = reference == null ? null : (PictureImpl) reference.get();
        if (pictureImpl != null && !pictureImpl.isDownloading()) {
            return pictureImpl;
        }
        pictureImpl = new PictureImpl(type, j);
        this.b.put(a, new WeakReference(pictureImpl, this.a));
        return pictureImpl;
    }

    public PictureImpl a(String str, Type type, long j) {
        Object a;
        a(this.a, this.b);
        if (TextUtils.isEmpty(str)) {
            a = a(type, Long.toString(j));
        } else {
            String a2 = a(Type.kPicWithUri, d.c(str).substring(0, 16));
        }
        Reference reference = (Reference) this.b.get(a);
        PictureImpl pictureImpl = reference == null ? null : (PictureImpl) reference.get();
        if (pictureImpl != null && !pictureImpl.isDownloading()) {
            return pictureImpl;
        }
        pictureImpl = new PictureImpl(str, type, j);
        this.b.put(a, new WeakReference(pictureImpl, this.a));
        return pictureImpl;
    }

    public Bitmap a(int i) {
        if (i == 0) {
            return null;
        }
        Reference reference = (Reference) this.e.get(i);
        Bitmap bitmap = reference == null ? null : (Bitmap) reference.get();
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = BitmapFactory.decodeResource(BaseApplication.getAppContext().getResources(), i);
        if (bitmap == null) {
            return bitmap;
        }
        this.e.put(i, new SoftReference(bitmap));
        return bitmap;
    }
}
