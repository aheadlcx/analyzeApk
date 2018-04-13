package cn.xiaochuankeji.tieba.ui.widget.overscroll;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import c.a.d.a.a;
import c.a.i.u;
import cn.xiaochuankeji.tieba.R;

public class RefreshTopView extends LinearLayout implements u {
    private AnimationDrawable a;

    public RefreshTopView(Context context) {
        this(context, null);
    }

    public RefreshTopView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RefreshTopView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        setOrientation(0);
        int applyDimension = (int) TypedValue.applyDimension(1, 8.0f, getResources().getDisplayMetrics());
        setPadding(0, applyDimension, 0, applyDimension);
        a();
    }

    public void a() {
        d();
        this.a.stop();
    }

    public void b() {
        this.a.start();
    }

    public void d() {
        View appCompatImageView = new AppCompatImageView(getContext());
        appCompatImageView.setImageDrawable(a.a().b(R.drawable.anim_recommend_refresh));
        this.a = (AnimationDrawable) appCompatImageView.getDrawable();
        FrameLayout frameLayout = new FrameLayout(getContext());
        int applyDimension = (int) TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(applyDimension, applyDimension);
        layoutParams.gravity = 17;
        layoutParams.setMargins(0, 0, applyDimension >> 1, 0);
        frameLayout.addView(appCompatImageView, layoutParams);
    }
}
