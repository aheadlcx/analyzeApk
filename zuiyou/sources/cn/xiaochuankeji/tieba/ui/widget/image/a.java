package cn.xiaochuankeji.tieba.ui.widget.image;

import android.net.Uri;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.common.c.b;
import com.facebook.imagepipeline.d.j;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;

public abstract class a {
    private String a;
    private int b;

    public abstract boolean a();

    public void a(String str) {
        this.a = str;
    }

    public String b() {
        return this.a;
    }

    public void a(int i) {
        this.b = i;
    }

    public String c() {
        if (this.b > 0) {
            return "res:///" + this.b;
        }
        return null;
    }

    public boolean d() {
        Object e = e();
        if (TextUtils.isEmpty(e)) {
            return false;
        }
        return new File(e).exists();
    }

    public String e() {
        ImageRequest a = ImageRequest.a(Uri.parse(b()));
        if (b.a() == null) {
            return null;
        }
        com.facebook.a.a a2 = j.a().h().a(b.a().c(a, null));
        return a2 != null ? ((com.facebook.a.b) a2).c().getAbsolutePath() : null;
    }
}
