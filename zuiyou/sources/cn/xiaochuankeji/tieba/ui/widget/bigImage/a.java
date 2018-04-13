package cn.xiaochuankeji.tieba.ui.widget.bigImage;

import android.net.Uri;
import android.support.annotation.UiThread;
import android.view.View;
import java.io.File;

public interface a {

    public interface a {
        @UiThread
        void a();

        @UiThread
        void a(int i);

        @UiThread
        void a(File file);

        @UiThread
        void a(Throwable th);

        @UiThread
        void b();

        @UiThread
        void b(File file);
    }

    public static class b implements a {
        public void a(File file) {
        }

        public void a() {
        }

        public void a(int i) {
        }

        public void b() {
        }

        public void a(Throwable th) {
        }

        public void b(File file) {
        }
    }

    View a(BigImageView bigImageView, Uri uri, int i);

    void a(int i);

    void a(int i, Uri uri, a aVar);
}
