package cn.xiaochuankeji.tieba.ui.widget.bigImage;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.WorkerThread;
import com.facebook.datasource.a;
import com.facebook.datasource.b;
import com.facebook.imagepipeline.g.d;

public abstract class c extends a<com.facebook.common.references.a<com.facebook.imagepipeline.g.c>> {
    private final Uri a;
    private volatile boolean b = false;

    @WorkerThread
    protected abstract void a(int i);

    @WorkerThread
    protected abstract void a(Bitmap bitmap);

    @WorkerThread
    protected abstract void a(Throwable th);

    public c(Uri uri) {
        this.a = uri;
    }

    public void a(b<com.facebook.common.references.a<com.facebook.imagepipeline.g.c>> bVar) {
        if (!this.b) {
            a((int) (bVar.g() * 100.0f));
        }
    }

    protected void b(b<com.facebook.common.references.a<com.facebook.imagepipeline.g.c>> bVar) {
        if (!bVar.b() || bVar.d() == null) {
            a(new IllegalStateException("dataSource result is null"));
            return;
        }
        com.facebook.imagepipeline.g.c cVar = (com.facebook.imagepipeline.g.c) ((com.facebook.common.references.a) bVar.d()).a();
        if (cVar != null && (cVar instanceof d)) {
            a(((d) cVar).f());
            this.b = true;
        }
    }

    protected void c(b<com.facebook.common.references.a<com.facebook.imagepipeline.g.c>> bVar) {
        this.b = true;
        if (bVar == null) {
            a(new RuntimeException("onFailureImpl"));
        } else {
            a(new RuntimeException("onFailureImpl", bVar.f()));
        }
    }
}
