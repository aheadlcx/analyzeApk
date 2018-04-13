package cn.xiaochuankeji.tieba.ui.widget.bigImage;

import android.net.Uri;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import cn.htjyb.c.d;
import cn.xiaochuan.base.BaseApplication;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.h;
import com.facebook.datasource.a;
import com.facebook.datasource.b;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class g extends a<com.facebook.common.references.a<PooledByteBuffer>> {
    private File a;
    private String b = cn.xiaochuankeji.tieba.background.a.e().k();
    private volatile boolean c;
    private Uri d;

    @WorkerThread
    protected abstract void a(int i);

    @WorkerThread
    protected abstract void a(File file);

    @WorkerThread
    protected abstract void a(Throwable th);

    protected g(Uri uri) {
        this.d = uri;
    }

    public void a(b<com.facebook.common.references.a<PooledByteBuffer>> bVar) {
        if (!this.c) {
            a((int) (bVar.g() * 100.0f));
        }
    }

    protected void b(b<com.facebook.common.references.a<PooledByteBuffer>> bVar) {
        InputStream hVar;
        Throwable e;
        InputStream inputStream;
        OutputStream outputStream = null;
        if (bVar.b() && bVar.d() != null) {
            try {
                File file;
                if (TextUtils.isEmpty(this.b)) {
                    this.b = cn.xiaochuankeji.tieba.background.a.e().k();
                }
                File file2 = new File(this.b);
                try {
                    if (!file2.exists()) {
                        org.apache.commons.io.b.e(file2);
                    }
                    file = file2;
                } catch (IOException e2) {
                    file = BaseApplication.getAppContext().getCacheDir();
                }
                hVar = new h((PooledByteBuffer) ((com.facebook.common.references.a) bVar.d()).a());
                try {
                    this.a = new File(file, d.c(this.d.toString()));
                    try {
                        if (this.a.exists()) {
                            org.apache.commons.io.b.d(this.a);
                        } else {
                            this.a.createNewFile();
                        }
                    } catch (IOException e3) {
                        this.a = new File(BaseApplication.getAppContext().getCacheDir(), d.c(this.d.toString()));
                        this.a.createNewFile();
                    }
                    OutputStream fileOutputStream = new FileOutputStream(this.a);
                    try {
                        f.a(hVar, fileOutputStream);
                        this.c = true;
                        a(this.a);
                        f.a(hVar);
                        f.a(fileOutputStream);
                    } catch (Exception e4) {
                        e = e4;
                        outputStream = fileOutputStream;
                        inputStream = hVar;
                        try {
                            e.printStackTrace();
                            cn.xiaochuankeji.tieba.analyse.a.a(e);
                            a(e);
                            f.a(inputStream);
                            f.a(outputStream);
                        } catch (Throwable th) {
                            e = th;
                            hVar = inputStream;
                            f.a(hVar);
                            f.a(outputStream);
                            throw e;
                        }
                    } catch (Throwable th2) {
                        e = th2;
                        outputStream = fileOutputStream;
                        f.a(hVar);
                        f.a(outputStream);
                        throw e;
                    }
                } catch (Exception e5) {
                    e = e5;
                    inputStream = hVar;
                    e.printStackTrace();
                    cn.xiaochuankeji.tieba.analyse.a.a(e);
                    a(e);
                    f.a(inputStream);
                    f.a(outputStream);
                } catch (Throwable th3) {
                    e = th3;
                    f.a(hVar);
                    f.a(outputStream);
                    throw e;
                }
            } catch (Exception e6) {
                e = e6;
                inputStream = null;
                e.printStackTrace();
                cn.xiaochuankeji.tieba.analyse.a.a(e);
                a(e);
                f.a(inputStream);
                f.a(outputStream);
            } catch (Throwable th4) {
                e = th4;
                hVar = null;
                f.a(hVar);
                f.a(outputStream);
                throw e;
            }
        }
    }

    protected void c(b<com.facebook.common.references.a<PooledByteBuffer>> bVar) {
        this.c = true;
        if (bVar == null) {
            a(new RuntimeException("onFailureImpl"));
        } else {
            a(new RuntimeException("onFailureImpl", bVar.f()));
        }
    }
}
