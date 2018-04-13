package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView.ScaleType;
import cn.htjyb.b.a.a;
import cn.htjyb.c.b.b;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;

public class PictureView extends e implements a, cn.htjyb.ui.a {
    private static int a = 4;
    private int b;
    private cn.htjyb.b.a c;
    private cn.htjyb.b.a d;
    private Bitmap e;
    private AsyncTask f;

    public PictureView(Context context) {
        this(context, null);
    }

    public PictureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setScaleType(ScaleType.CENTER_CROP);
        this.b = cn.htjyb.c.a.a((float) a, BaseApplication.getAppContext());
    }

    public PictureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setScaleType(ScaleType.CENTER_CROP);
        this.b = cn.htjyb.c.a.a((float) a, BaseApplication.getAppContext());
    }

    public void setData(cn.htjyb.b.a aVar) {
        if (this.c != aVar) {
            c();
            this.c = aVar;
            if (aVar == null) {
                return;
            }
            if (aVar.hasLocalFile()) {
                setLocalFileImage(aVar);
                return;
            }
            setPlaceholderBitmap(aVar);
            if (aVar.canDownload()) {
                this.d = aVar;
                aVar.registerPictureDownloadListener(this);
                aVar.download(false);
            }
        }
    }

    private void setPlaceholderBitmap(cn.htjyb.b.a aVar) {
        Bitmap placeholderBitmap = aVar.getPlaceholderBitmap();
        Bitmap a = a(aVar, placeholderBitmap, false);
        if (a == null) {
            setImageBitmap(placeholderBitmap);
        } else {
            setImageBitmap(a);
        }
    }

    private Bitmap a(cn.htjyb.b.a aVar, Bitmap bitmap, boolean z) {
        if (Type.kAvatarMale == aVar.getType() || Type.kAvatarFemale == aVar.getType()) {
            return b.a(bitmap, z);
        }
        if (Type.kTopicCover == aVar.getType()) {
            return b.a(0, bitmap, this.b);
        }
        return null;
    }

    private void setLocalFileImage(final cn.htjyb.b.a aVar) {
        setImageBitmap(null);
        this.e = a(aVar, aVar.getActualBitmap(), true);
        if (this.e != null) {
            setImageBitmap(this.e);
            return;
        }
        if (this.f != null) {
            this.f.cancel(true);
            c();
        }
        this.f = new AsyncTask(this) {
            final /* synthetic */ PictureView b;

            protected Object doInBackground(Object[] objArr) {
                this.b.e = aVar.getActualBitmap();
                return null;
            }

            protected void onPostExecute(Object obj) {
                super.onPostExecute(obj);
                this.b.setImageBitmap(this.b.e);
                this.b.f = null;
            }
        };
        this.f.execute(new Object[]{"123"});
    }

    private void a() {
        if (this.d != null) {
            this.d.unregisterPictureDownloadListener(this);
            this.d.cancelDownload();
            this.d = null;
        }
    }

    public void a(cn.htjyb.b.a aVar, boolean z, int i, String str) {
        if (z) {
            setLocalFileImage(aVar);
            a();
            return;
        }
        com.izuiyou.a.a.b.e("xxx_图片下载失败!!!tips:" + str);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    public void c() {
        this.c = null;
        a();
        if (this.e != null) {
            setImageBitmap(null);
            this.e.recycle();
            this.e = null;
        }
    }
}
