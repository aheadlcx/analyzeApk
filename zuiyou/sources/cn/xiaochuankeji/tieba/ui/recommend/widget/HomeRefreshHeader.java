package cn.xiaochuankeji.tieba.ui.recommend.widget;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import c.a.d.a.a;
import c.a.i.u;
import cn.xiaochuankeji.tieba.R;
import com.scwang.smartrefresh.layout.a.e;
import com.scwang.smartrefresh.layout.a.g;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public class HomeRefreshHeader extends RelativeLayout implements u, e {
    private AnimationDrawable a;
    private ImageView b;
    private g c;
    private SpinnerStyle d = SpinnerStyle.Translate;

    /* renamed from: cn.xiaochuankeji.tieba.ui.recommend.widget.HomeRefreshHeader$1 */
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
                a[RefreshState.ReleaseToRefresh.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[RefreshState.Refreshing.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[RefreshState.RefreshReleased.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[RefreshState.RefreshFinish.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[RefreshState.PullDownCanceled.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public HomeRefreshHeader(@NonNull Context context) {
        super(context);
        a(context);
    }

    public HomeRefreshHeader(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public HomeRefreshHeader(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.a = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_hollow_refresh);
        this.b = new ImageView(context);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(cn.xiaochuankeji.tieba.ui.utils.e.a(53.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(53.0f));
        layoutParams.addRule(13, -1);
        this.b.setLayoutParams(layoutParams);
        this.b.setScaleType(ScaleType.FIT_CENTER);
        this.b.setImageDrawable(this.a);
        addView(this.b);
    }

    public void a(g gVar, int i, int i2) {
        this.c = gVar;
        this.c.a(a.a().a((int) R.color.transparent));
    }

    public void a(float f, int i, int i2, int i3) {
    }

    public void b(float f, int i, int i2, int i3) {
    }

    public void a(h hVar, int i, int i2) {
        if (this.a != null) {
            this.a.start();
            return;
        }
        Drawable drawable = this.b.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    @NonNull
    public View getView() {
        return this;
    }

    public SpinnerStyle getSpinnerStyle() {
        return this.d;
    }

    public void setPrimaryColors(int... iArr) {
    }

    public void a(float f, int i, int i2) {
    }

    public void b(h hVar, int i, int i2) {
    }

    public int a(h hVar, boolean z) {
        if (this.a != null) {
            this.a.stop();
        } else {
            Drawable drawable = this.b.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).stop();
            }
        }
        this.b.setVisibility(8);
        return 0;
    }

    public boolean a() {
        return false;
    }

    public void a(h hVar, RefreshState refreshState, RefreshState refreshState2) {
        switch (AnonymousClass1.a[refreshState2.ordinal()]) {
            case 1:
            case 2:
                this.b.setVisibility(0);
                return;
            case 3:
                this.a.start();
                return;
            case 6:
            case 7:
                this.b.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void d() {
        this.a = (AnimationDrawable) a.a().b(R.drawable.anim_recommend_refresh);
        this.b.setImageDrawable(this.a);
        setBackgroundColor(0);
    }
}
