package cn.xiaochuankeji.tieba.ui.widget.bigImage;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.a.d;
import cn.xiaochuankeji.tieba.ui.widget.bigImage.a.a;
import com.facebook.datasource.b;
import com.facebook.drawee.a.a.c;
import com.facebook.drawee.drawable.n$b;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class e implements a {
    private final Context a;
    private final ConcurrentHashMap<Integer, b> b = new ConcurrentHashMap();

    private e(Context context) {
        this.a = context;
    }

    public static e a(Context context) {
        return new e(context);
    }

    public void a(int i, final Uri uri, final a aVar) {
        ImageRequest a = ImageRequest.a(uri);
        rx.a.b.a.a().a().a(new rx.b.a(this) {
            final /* synthetic */ e b;

            public void call() {
                aVar.a();
                aVar.a(0);
            }
        });
        b(i);
        b b = c.c().b(a, Boolean.valueOf(true));
        b.a(new g(this, uri) {
            final /* synthetic */ e c;

            protected void a(final int i) {
                rx.a.b.a.a().a().a(new rx.b.a(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void call() {
                        aVar.a(i);
                    }
                });
            }

            protected void a(final File file) {
                rx.a.b.a.a().a().a(new rx.b.a(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void call() {
                        aVar.a(file);
                        aVar.b(file);
                        aVar.b();
                    }
                });
            }

            protected void a(final Throwable th) {
                com.izuiyou.a.a.b.e(th);
                cn.xiaochuankeji.tieba.analyse.a.a(th);
                rx.a.b.a.a().a().a(new rx.b.a(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void call() {
                        aVar.a(th);
                    }
                });
                if (uri != null && th != null) {
                    d.a().a(uri.toString(), th.getMessage());
                }
            }
        }, cn.xiaochuankeji.tieba.background.a.p().d());
        a(i, b);
    }

    public View a(BigImageView bigImageView, Uri uri, int i) {
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) LayoutInflater.from(bigImageView.getContext()).inflate(R.layout.ui_fresco_thumbnail, bigImageView, false);
        com.facebook.drawee.d.a k = c.a().a(uri).k();
        switch (i) {
            case 1:
                ((com.facebook.drawee.generic.a) simpleDraweeView.getHierarchy()).a(n$b.f);
                break;
            case 2:
                ((com.facebook.drawee.generic.a) simpleDraweeView.getHierarchy()).a(n$b.g);
                break;
        }
        simpleDraweeView.setController(k);
        return simpleDraweeView;
    }

    public void a(int i) {
        b(i);
    }

    private void a(int i, b bVar) {
        this.b.put(Integer.valueOf(i), bVar);
    }

    private void b(int i) {
        b bVar = (b) this.b.remove(Integer.valueOf(i));
        if (bVar != null) {
            bVar.h();
        }
    }
}
