package cn.xiaochuan.image.b;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.widget.ImageView;
import com.facebook.drawee.a.a.e;
import com.facebook.drawee.drawable.n$b;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.common.d;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

@TargetApi(12)
public class b implements OnAttachStateChangeListener {
    private Context a;
    private com.facebook.drawee.view.b<com.facebook.drawee.d.b> b = null;
    private Uri c;
    private Drawable d = null;
    private Drawable e = null;
    private Drawable f = null;
    private c g = null;
    private d h = d.a();
    private float i = 0.0f;
    private PointF j = null;
    private n$b k = com.facebook.drawee.generic.b.a;
    private n$b l = com.facebook.drawee.generic.b.a;
    private n$b m = com.facebook.drawee.generic.b.a;
    private n$b n = com.facebook.drawee.generic.b.b;
    private Drawable o = null;
    private RoundingParams p = null;
    private int q = 300;
    private boolean r = false;
    private boolean s = true;

    b(Context context) {
        this.a = context.getApplicationContext();
    }

    public static b a(Context context) {
        return new b(context);
    }

    public b a(Uri uri) {
        this.c = uri;
        return this;
    }

    public b a(n$b n_b) {
        this.n = n_b;
        return this;
    }

    public b a(Drawable drawable) {
        this.d = drawable;
        return this;
    }

    public b a(float f) {
        this.i = f;
        return this;
    }

    public b a(int i, int i2) {
        this.g = new c(i, i2);
        return this;
    }

    public b a(int i) {
        if (this.p == null) {
            this.p = new RoundingParams();
        }
        this.p.a((float) i);
        return this;
    }

    public b a(boolean z) {
        this.r = z;
        return this;
    }

    public b b(boolean z) {
        this.s = z;
        return this;
    }

    public void a(ImageView imageView) {
        if (imageView != null && this.c != null) {
            if (this.b == null) {
                Object tag = imageView.getTag();
                if (tag instanceof com.facebook.drawee.view.b) {
                    this.b = (com.facebook.drawee.view.b) tag;
                }
            }
            if (this.b == null) {
                this.b = com.facebook.drawee.view.b.a(null, imageView.getContext());
                this.b.a(new com.facebook.drawee.generic.b(imageView.getResources()).a(this.q).a(this.d, this.k).b(this.e, this.l).c(this.f, this.m).e(this.n).a(this.j).e(this.o).a(this.i).a(this.p).t());
                this.b.a(((e) ((e) ((e) ((e) com.facebook.drawee.a.a.c.a().a(this.b.d())).b(ImageRequestBuilder.a(this.c).a(this.g).b(true).c(this.s).a(Priority.HIGH).a(this.h).n())).b(this.r)).a(false)).k());
                if (a((View) imageView)) {
                    this.b.b();
                }
                imageView.addOnAttachStateChangeListener(this);
                imageView.setTag(this.b);
            } else {
                this.b.a(new com.facebook.drawee.generic.b(imageView.getResources()).a(this.q).a(this.d, this.k).b(this.e, this.l).c(this.f, this.m).e(this.n).a(this.j).e(this.o).a(this.i).a(this.p).t());
                this.b.a(((e) ((e) ((e) ((e) com.facebook.drawee.a.a.c.a().a(this.b.d())).b(ImageRequestBuilder.a(this.c).a(this.g).c(this.s).a(Priority.HIGH).a(this.h).n())).b(this.r)).a(false)).k());
            }
            imageView.setImageDrawable(this.b.f());
        }
    }

    private static boolean a(View view) {
        if (VERSION.SDK_INT >= 19) {
            return view.isAttachedToWindow();
        }
        return view.getWindowToken() != null;
    }

    public void onViewAttachedToWindow(View view) {
        this.b.b();
    }

    public void onViewDetachedFromWindow(View view) {
        this.b.c();
    }
}
