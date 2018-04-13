package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import com.scwang.smartrefresh.layout.a.e;
import com.scwang.smartrefresh.layout.a.g;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public class ZYRefreshHeader extends RelativeLayout implements e {
    private ImageView a;
    private AnimationDrawable b;
    private g c;

    /* renamed from: cn.xiaochuankeji.tieba.ui.widget.ZYRefreshHeader$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[RefreshState.values().length];

        static {
            try {
                a[RefreshState.None.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[RefreshState.PullDownToRefresh.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[RefreshState.Refreshing.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[RefreshState.ReleaseToRefresh.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[RefreshState.Loading.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public ZYRefreshHeader(Context context) {
        super(context);
        b();
    }

    public ZYRefreshHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public ZYRefreshHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        this.a = new ImageView(getContext());
        this.a.setScaleType(ScaleType.CENTER);
        this.b = (AnimationDrawable) a.a().b(R.drawable.anim_recommend_refresh);
        this.a.setImageDrawable(this.b);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        addView(this.a, layoutParams);
    }

    public void a(float f, int i, int i2, int i3) {
    }

    public void b(float f, int i, int i2, int i3) {
    }

    public void a(h hVar, int i, int i2) {
    }

    @NonNull
    public View getView() {
        return this;
    }

    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    public void setPrimaryColors(int... iArr) {
    }

    public void a(g gVar, int i, int i2) {
        this.c = gVar;
        this.c.a(a.a().a((int) R.color.CL));
    }

    public void a(float f, int i, int i2) {
    }

    public void b(h hVar, int i, int i2) {
        this.b.start();
    }

    public int a(h hVar, boolean z) {
        this.b.stop();
        return 0;
    }

    public boolean a() {
        return false;
    }

    public void a(h hVar, RefreshState refreshState, RefreshState refreshState2) {
        if (this.c != null) {
            this.c.a(a.a().a((int) R.color.CL));
        }
        this.b = (AnimationDrawable) a.a().b(R.drawable.anim_recommend_refresh);
        this.a.setImageDrawable(this.b);
        switch (AnonymousClass1.a[refreshState2.ordinal()]) {
            case 3:
                this.b.start();
                return;
            default:
                return;
        }
    }
}
