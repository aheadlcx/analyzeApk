package cn.xiaochuankeji.tieba.ui.homepage;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import c.a.i.u;
import cn.xiaochuankeji.tieba.R;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.a.a;
import in.srain.cube.views.ptr.b;

public class e extends LinearLayout implements u, b {
    private ImageView a;
    private AnimationDrawable b;
    @ColorRes
    private int c;

    public e(Context context) {
        this(context, 0);
    }

    public e(Context context, @ColorRes int i) {
        super(context);
        a(context);
        this.c = i;
    }

    private void a(Context context) {
        this.a = new ImageView(context);
        addView(this.a, new LayoutParams(-1, cn.xiaochuankeji.tieba.ui.utils.e.a(53.0f)));
        this.a.setScaleType(ScaleType.CENTER);
    }

    public void a(PtrFrameLayout ptrFrameLayout) {
        b();
    }

    private void b() {
        d();
        if (this.b != null && this.b.isRunning()) {
            this.b.stop();
        }
    }

    public void a() {
        this.b.start();
    }

    public void b(PtrFrameLayout ptrFrameLayout) {
        b();
    }

    public void c(PtrFrameLayout ptrFrameLayout) {
    }

    public void d(PtrFrameLayout ptrFrameLayout) {
        if (this.b != null && this.b.isRunning()) {
            this.b.stop();
        }
    }

    public void a(PtrFrameLayout ptrFrameLayout, boolean z, byte b, a aVar) {
        int offsetToRefresh = ptrFrameLayout.getOffsetToRefresh();
        int k = aVar.k();
        int j = aVar.j();
        if (k >= offsetToRefresh || j < offsetToRefresh) {
            if (k > offsetToRefresh && j <= offsetToRefresh && z && b == (byte) 2) {
                this.b.start();
            }
        } else if (z && b == (byte) 2) {
            this.b.stop();
        }
    }

    public void d() {
        int a = c.a.d.a.a.a().a(this.c <= 0 ? R.color.CL : this.c);
        Drawable b = c.a.d.a.a.a().b(R.drawable.anim_recommend_refresh);
        this.a.setBackgroundColor(a);
        this.a.setImageDrawable(b);
        this.b = (AnimationDrawable) this.a.getDrawable();
    }
}
